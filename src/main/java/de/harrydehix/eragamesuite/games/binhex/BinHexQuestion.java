package de.harrydehix.eragamesuite.games.binhex;

import de.harrydehix.eragamesuite.games.Question;

import java.util.Random;

public class BinHexQuestion extends Question<BinHexDifficulty, BinHexMode> {
    private static final Random RANDOM = new Random();

    private static BinHexMode chooseRandomDirection() {
        if (RANDOM.nextBoolean()) {
            return BinHexMode.BIN_TO_HEX;
        }
        else {
            return BinHexMode.HEX_TO_BIN;
        }
    }

    public BinHexQuestion(BinHexDifficulty difficulty, BinHexMode mode) {
        super(difficulty, mode == BinHexMode.MIXED ? chooseRandomDirection() : mode);
        generateQuestion();
    }

    private void generateQuestion() {
        String binary = generateBinary(getDifficulty().getNibbleCount());
        String hex = binaryToHex(binary);
        if (getMode() == BinHexMode.BIN_TO_HEX) {
            setQuestion(binary);
            setAnswer(hex);
        }
        else {
            setQuestion(hex);
            setAnswer(binary);
        }
    }

    private String generateBinary(int nibbleCount) {
        StringBuilder nibble = new StringBuilder();
        int bitCount = nibbleCount * 4;
        for (int bit = 0; bit < bitCount; bit++) {
            nibble.append(RANDOM.nextInt(0, 2));
            if ((bit + 1) % 4 == 0 && (bit + 1) < bitCount) {
                nibble.append('_');
            }
        }
        return nibble.toString();
    }

    protected boolean validateAnswer(String answer) {
        return answer.trim().equals(getAnswer());
    }

    private String binaryToHex(String binary) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 4) {
            if (binary.charAt(i) == '_') {
                i++;
            }
            String nibble = binary.substring(i, i + 4);
            switch (nibble) {
                case "0000":
                    hex.append('0');
                    break;
                case "0001":
                    hex.append('1');
                    break;
                case "0010":
                    hex.append('2');
                    break;
                case "0011":
                    hex.append('3');
                    break;
                case "0100":
                    hex.append('4');
                    break;
                case "0101":
                    hex.append('5');
                    break;
                case "0110":
                    hex.append('6');
                    break;
                case "0111":
                    hex.append('7');
                    break;
                case "1000":
                    hex.append('8');
                    break;
                case "1001":
                    hex.append('9');
                    break;
                case "1010":
                    hex.append('A');
                    break;
                case "1011":
                    hex.append('B');
                    break;
                case "1100":
                    hex.append('C');
                    break;
                case "1101":
                    hex.append('D');
                    break;
                case "1110":
                    hex.append('E');
                    break;
                case "1111":
                    hex.append('F');
                    break;
            }
        }
        return hex.toString();
    }
}
