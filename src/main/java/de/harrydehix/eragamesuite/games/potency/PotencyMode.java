package de.harrydehix.eragamesuite.games.potency;

import de.harrydehix.eragamesuite.games.Mode;

public class PotencyMode extends Mode {
    public static final PotencyMode POT_TO_DEC = new PotencyMode("PotToDec", 1);
    public static final PotencyMode DEC_TO_POT = new PotencyMode("DecToPot", 1);
    public static final PotencyMode MIXED = new PotencyMode("Mixed", 1.5);
    public static final PotencyMode[] MODES = {
            POT_TO_DEC, DEC_TO_POT, MIXED
    };

    private PotencyMode(String name, double weight) {
        super(name, weight);
    }
}
