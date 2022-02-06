package de.harrydehix.eragamesuite.games.binhex;

import de.harrydehix.eragamesuite.games.Game;

public class BinHexGame extends Game<BinHexQuestion, BinHexDifficulty, BinHexMode> {

    public BinHexGame(int questionCount, BinHexDifficulty difficulty, BinHexMode mode) {
        super(questionCount, difficulty, mode, BinHexQuestion.class);
        super.generateQuestions();
    }

    @Override
    public BinHexQuestion generateQuestion() {
        return new BinHexQuestion(getDifficulty(), getMode());
    }

    @Override
    protected double calculateScore() {
        return (getStatistics().getCorrectAnswerQuote() * getDifficulty().getWeight() * getMode().getWeight() * getQuestions().length) / getStatistics().getAnswerTimePerQuestion();
    }
}
