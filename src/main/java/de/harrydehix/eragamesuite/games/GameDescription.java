package de.harrydehix.eragamesuite.games;

import de.harrydehix.eragamesuite.games.binhex.BinHexDifficulty;
import de.harrydehix.eragamesuite.games.binhex.BinHexGame;
import de.harrydehix.eragamesuite.games.binhex.BinHexMode;
import de.harrydehix.eragamesuite.games.potency.PotencyDifficulty;
import de.harrydehix.eragamesuite.games.potency.PotencyGame;
import de.harrydehix.eragamesuite.games.potency.PotencyMode;

import java.lang.reflect.InvocationTargetException;

public enum GameDescription {
    BIN_HEX("BinHex", "Learn to read binaries and hexadecimals!", BinHexDifficulty.DIFFICULTIES, BinHexMode.MODES, BinHexGame.class),
    POTENCY("2s Potency", "Gameeeeeee", PotencyDifficulty.DIFFICULTIES, PotencyMode.MODES, PotencyGame.class);

    private final String gameName, gameDescription;
    private final Difficulty[] difficulties;
    private final Mode[] modes;
    private final Class<? extends Game> gameClass;
    private final Class<? extends Difficulty> difficultyClass;
    private final Class<? extends Mode> modeClass;

    GameDescription(String gameName, String gameDescription, Difficulty[] difficulties, Mode[] modes, Class<? extends Game> gameClass) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.difficulties = difficulties;
        this.modes = modes;
        this.gameClass = gameClass;
        Class<?>[] types = gameClass.getConstructors()[0].getParameterTypes();
        this.difficultyClass = (Class<? extends Difficulty>) types[1];
        this.modeClass = (Class<? extends Mode>) types[2];
    }

    public Difficulty[] getDifficulties() {
        return difficulties;
    }

    public Mode[] getModes() {
        return modes;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public Game createInstance(int questionCount, Difficulty difficulty, Mode gameMode) {
        if (questionCount < 1 || difficulty == null || gameMode == null) {
            return null;
        }
        try {
            return gameClass.getConstructor(int.class, difficultyClass, modeClass).newInstance(questionCount, difficulty, gameMode);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
