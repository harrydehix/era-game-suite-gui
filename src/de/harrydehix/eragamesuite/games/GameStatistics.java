package de.harrydehix.eragamesuite.games;

public class GameStatistics {
    private final int correctAnswers;
    private final int totalQuestions;
    private final double correctAnswerQuote;
    private final double answerTimePerQuestion;
    private final double answerTime;

    public GameStatistics(int correctAnswers, int totalQuestions, double answerTime) {
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.correctAnswerQuote = (double) correctAnswers / totalQuestions;
        this.answerTime = answerTime;
        this.answerTimePerQuestion = answerTime / totalQuestions;
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
}
