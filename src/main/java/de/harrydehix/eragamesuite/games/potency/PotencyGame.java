package de.harrydehix.eragamesuite.games.potency;

import de.harrydehix.eragamesuite.games.Game;
import de.harrydehix.eragamesuite.games.GameStatistics;

public class PotencyGame extends Game<PotencyQuestion, PotencyDifficulty, PotencyMode> {
    public static final long GAME_ID = 0;

    public PotencyGame(int questionCount, PotencyDifficulty difficulty, PotencyMode mode) {
        super(questionCount, difficulty, mode, PotencyQuestion.class, GAME_ID);
        super.generateQuestions();
    }

    @Override
    public PotencyQuestion generateQuestion() {
        return new PotencyQuestion(getDifficulty(), getMode());
    }

    @Override
    protected Double calculateScore(GameStatistics stats) {
        return (stats.getCorrectAnswerQuote() * stats.getDifficulty().getWeight() * stats.getTotalQuestions()) / stats.getAnswerTimePerQuestion();
    }
}
