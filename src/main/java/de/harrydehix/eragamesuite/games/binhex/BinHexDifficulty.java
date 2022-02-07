package de.harrydehix.eragamesuite.games.binhex;

import de.harrydehix.eragamesuite.games.Difficulty;

public class BinHexDifficulty extends Difficulty {
    public static final BinHexDifficulty EASY = new BinHexDifficulty("Easy", 1, 1);
    public static final BinHexDifficulty MEDIUM = new BinHexDifficulty("Medium", 2, 2.5);
    public static final BinHexDifficulty DIFFICULT = new BinHexDifficulty("Difficult", 3, 4);
    public static final BinHexDifficulty HEAVY = new BinHexDifficulty("Heavy", 4, 6);
    public static final BinHexDifficulty[] DIFFICULTIES = new BinHexDifficulty[]{
            EASY, MEDIUM, DIFFICULT, HEAVY
    };

    private final int nibbleCount;

    private BinHexDifficulty(String name, int nibbleCount, double weight) {
        super(name, weight);
        this.nibbleCount = nibbleCount;
    }

    public int getNibbleCount() {
        return nibbleCount;
    }
}
