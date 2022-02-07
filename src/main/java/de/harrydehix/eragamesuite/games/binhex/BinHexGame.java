package de.harrydehix.eragamesuite.games.binhex;

import de.harrydehix.eragamesuite.games.Game;
import de.harrydehix.eragamesuite.games.GameStatistics;

public class BinHexGame extends Game<BinHexQuestion, BinHexDifficulty, BinHexMode> {
    public static final long GAME_ID = 1;

    public BinHexGame(int questionCount, BinHexDifficulty difficulty, BinHexMode mode) {
        super(questionCount, difficulty, mode, BinHexQuestion.class, GAME_ID);
        super.generateQuestions();
    }

    @Override
    public BinHexQuestion generateQuestion() {
        return new BinHexQuestion(getDifficulty(), getMode());
    }

    @Override
    protected Double calculateScore(GameStatistics stats) {
        return (stats.getCorrectAnswerQuote() * stats.getDifficulty().getWeight() * stats.getMode().getWeight() * stats.getTotalQuestions()) / stats.getAnswerTimePerQuestion();
    }
}
