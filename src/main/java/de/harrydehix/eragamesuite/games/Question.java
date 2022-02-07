package de.harrydehix.eragamesuite.games;

import java.util.Objects;

public abstract class Question<D extends Difficulty, M extends Mode> {
    private String question;
    private String answer;
    private boolean result;
    private final Timer questionTimer;
    private final D difficulty;
    private final M mode;

    public Question(D difficulty, M mode) {
        this.difficulty = difficulty;
        this.mode = mode;
        questionTimer = new Timer();
    }

    public void startQuestionTimer() {
        questionTimer.start();
    }

    public boolean solve(String answer) {
        result = validateAnswer(answer);
        questionTimer.stop();
        return result;
    }

    protected abstract boolean validateAnswer(String answer);

    public String getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

    protected void setQuestion(String question) {
        this.question = question;
    }

    protected void setAnswer(String answer) {
        this.answer = answer;
    }

    public Timer getQuestionTimer() {
        return questionTimer;
    }

    public boolean getResult() {
        return result;
    }

    public M getMode() {
        return mode;
    }

    public D getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return question + ": " + answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question that)) {
            return false;
        }
        return Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }
}
