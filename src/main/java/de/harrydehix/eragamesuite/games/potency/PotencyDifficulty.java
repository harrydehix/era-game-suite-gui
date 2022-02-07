package de.harrydehix.eragamesuite.games.potency;

import de.harrydehix.eragamesuite.games.Difficulty;

public class PotencyDifficulty extends Difficulty {
    public static final PotencyDifficulty EASY = new PotencyDifficulty("Easy", 4, 1);
    public static final PotencyDifficulty MEDIUM = new PotencyDifficulty("Medium", 6, 2);
    public static final PotencyDifficulty DIFFICULT = new PotencyDifficulty("Difficult", 8, 3.5);
    public static final PotencyDifficulty HEAVY = new PotencyDifficulty("Heavy", 12, 5);

    public static final PotencyDifficulty[] DIFFICULTIES = new PotencyDifficulty[]{
            EASY, MEDIUM, DIFFICULT, HEAVY
    };

    private final int maxPotency;

    private PotencyDifficulty(String name, int maxPotency, double weight) {
        super(name, weight);
        this.maxPotency = maxPotency;
    }

    public int getMaxPotency() {
        return maxPotency;
    }
}