package de.harrydehix.eragamesuite.games;

import org.json.JSONObject;

import java.util.function.Function;

public class GameStatistics {
    private final int correctAnswers;
    private final int totalQuestions;
    private final double correctAnswerQuote;
    private final double answerTimePerQuestion;
    private final double answerTime;
    private final double score;
    private final Difficulty difficulty;
    private final Mode mode;
    private final Game game;

    public GameStatistics(int correctAnswers, int totalQuestions, double answerTime, Function<GameStatistics, Double> scoreFunction, Difficulty difficulty, Mode mode, Game game) {
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.difficulty = difficulty;
        this.mode = mode;
        this.game = game;
        this.correctAnswerQuote = (double) correctAnswers / totalQuestions;
        this.answerTime = answerTime;
        this.answerTimePerQuestion = answerTime / totalQuestions;
        this.score = scoreFunction.apply(this);
    }

    public Game getGame() {
        return game;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Mode getMode() {
        return mode;
    }

    public double getScore() {
        return score;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public double getCorrectAnswerQuote() {
        return correctAnswerQuote;
    }

    public double getAnswerTimePerQuestion() {
        return answerTimePerQuestion;
    }

    public double getAnswerTime() {
        return answerTime;
    }

    public JSONObject toJSONObject() {
        JSONObject result = new JSONObject();

        result.put("correctAnswers", correctAnswers);
        result.put("totalQuestions", totalQuestions);
        result.put("correctAnswerQuote", correctAnswerQuote);
        result.put("answerTimePerQuestion", answerTimePerQuestion);
        result.put("answerTime", answerTime);
        result.put("score", score);
        result.put("difficulty", difficulty.toString());
        result.put("mode", mode.toString());
        result.put("gameId", getGame().getId());

        return result;
    }
}
