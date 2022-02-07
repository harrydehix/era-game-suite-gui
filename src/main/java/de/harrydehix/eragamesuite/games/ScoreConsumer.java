package de.harrydehix.eragamesuite.games;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class ScoreConsumer {
    private static final int MAX_HIGHSCORE_COUNT_PER_GAME = 10;
    private static ScoreConsumer instance = null;

    public static ScoreConsumer getInstance() throws IOException {
        if (instance == null) {
            instance = new ScoreConsumer();
        }
        return instance;
    }

    private JSONObject json;
    private String filepath = "target/classes/highscores.json";

    private ScoreConsumer() throws IOException {
        initialize();
    }

    public void initialize() throws IOException {
        String fileContent = Files.readString(Paths.get(filepath), StandardCharsets.UTF_8);
        json = new JSONObject(fileContent);
    }

    public void consume(GameStatistics statistics) throws IOException {
        String gameId = String.valueOf(statistics.getGame().getId());
        JSONArray highscores = json.getJSONArray(gameId);
        int indexToInsert = 0;
        if (highscores.length() > 0) {
            indexToInsert = -1;
            for (int i = 0; i < highscores.length(); i++) {
                Object object = highscores.get(i);
                if (object instanceof JSONObject highscore) {
                    if (statistics.getScore() > highscore.getDouble("score")) {
                        indexToInsert = i;
                        break;
                    }
                }
            }
            if (indexToInsert == -1) {
                if (highscores.length() >= MAX_HIGHSCORE_COUNT_PER_GAME) {
                    return;
                }
                else {
                    indexToInsert = highscores.length();
                }
            }
        }
        JSONArray newHighscores = new JSONArray();
        boolean afterInsertingHighscore = false;
        for (int i = 0; i < highscores.length() + 1; i++) {
            if (i == indexToInsert) {
                newHighscores.put(i, statistics.toJSONObject());
                afterInsertingHighscore = true;
            }
            else {
                newHighscores.put(i, highscores.get(afterInsertingHighscore ? i - 1 : i));
            }
        }
        json.put(gameId, newHighscores);
        System.out.println(newHighscores);
        updateJSONFile();
    }

    public void updateJSONFile() throws IOException {
        PrintWriter myFile = new PrintWriter(filepath, StandardCharsets.UTF_8);
        myFile.println(json.toString(4));
        myFile.flush();
        myFile.close();
        System.out.println("");
    }
}
