package de.harrydehix.eragamesuite.games.potency;

import de.harrydehix.eragamesuite.games.Game;

public class PotencyGame extends Game<PotencyQuestion, PotencyDifficulty, PotencyMode> {
    public PotencyGame(int questionCount, PotencyDifficulty difficulty, PotencyMode mode) {
        super(questionCount, difficulty, mode, PotencyQuestion.class);
        super.generateQuestions();
    }

    @Override
    public PotencyQuestion generateQuestion() {
        return new PotencyQuestion(getDifficulty(), getMode());
    }

    @Override
    protected double calculateScore() {
        return (getStatistics().getCorrectAnswerQuote() * getDifficulty().getWeight() * getQuestions().length) / getStatistics().getAnswerTimePerQuestion();
    }
}
