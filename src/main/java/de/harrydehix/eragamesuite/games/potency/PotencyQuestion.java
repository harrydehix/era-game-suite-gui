package de.harrydehix.eragamesuite.games.potency;

import de.harrydehix.eragamesuite.games.Question;

import java.util.Random;

public class PotencyQuestion extends Question<PotencyDifficulty, PotencyMode> {
    private static final Random RANDOM = new Random();

    private static PotencyMode chooseRandomDirection() {
        if (RANDOM.nextBoolean()) {
            return PotencyMode.DEC_TO_POT;
        }
        else {
            return PotencyMode.POT_TO_DEC;
        }
    }

    public PotencyQuestion(PotencyDifficulty difficulty, PotencyMode mode) {
        super(difficulty, mode == PotencyMode.MIXED ? chooseRandomDirection() : mode);
        generateQuestion();
    }

    private void generateQuestion() {
        int potency = RANDOM.nextInt(0, getDifficulty().getMaxPotency() + 1);
        int decimal = (int) Math.pow(2, potency);

        if (getMode() == PotencyMode.DEC_TO_POT) {
            setQuestion(String.valueOf(decimal));
            setAnswer(String.valueOf(potency));
        }
        else {
            setQuestion(2 + exponent(String.valueOf(potency)));
            setAnswer(String.valueOf(decimal));
        }
    }

    private static final char[] EXPONENTS = {'⁰', '¹', '²', '³', '⁴', '⁵', '⁶', '⁷', '⁸', '⁹'};

    private static String exponent(String number) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            result.append(EXPONENTS[Character.getNumericValue(number.charAt(i))]);
        }
        return result.toString();
    }


    protected boolean validateAnswer(String answer) {
        return answer.trim().equals(getAnswer());
    }
}
