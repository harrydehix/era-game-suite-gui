package de.harrydehix.eragamesuite.games.binhex;

import de.harrydehix.eragamesuite.games.Mode;

public class BinHexMode extends Mode {
    public static final BinHexMode BIN_TO_HEX = new BinHexMode("BinToHex", 1);
    public static final BinHexMode HEX_TO_BIN = new BinHexMode("HexToBin", 1);
    public static final BinHexMode MIXED = new BinHexMode("Mixed", 1.5);
    public static final BinHexMode[] MODES = {
            BIN_TO_HEX, HEX_TO_BIN, MIXED
    };

    private BinHexMode(String name, double weight) {
        super(name, weight);
    }
}
