package de.harrydehix.eragamesuite.games;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class Game<Q extends Question, D extends Difficulty, M extends Mode> implements Iterable<Q> {
    private final Q[] questions;
    private GameStatistics statistics;
    private final D difficulty;
    private final M mode;
    private Double score;

    public Game(int questionCount, D difficulty, M mode, Class<Q> questionClass) {
        questions = (Q[]) Array.newInstance(questionClass, questionCount);
        this.difficulty = difficulty;
        this.mode = mode;
    }

    protected void generateQuestions() {
        for (int i = 0; i < questions.length; i++) {
            do {
                questions[i] = generateQuestion();
            } while (i > 0 && questions[i - 1].equals(questions[i]));
        }
    }

    public abstract Q generateQuestion();

    public void generateStatistics() {
        Timer timer = new Timer();
        int correctAnswers = 0;
        for (Q question : this) {
            timer.add(question.getQuestionTimer());
            if (question.getResult()) {
                correctAnswers++;
            }
        }
        this.statistics = new GameStatistics(correctAnswers, getQuestions().length, TimeUtils.seconds(timer.getMeasuredTime()));
        this.score = calculateScore();
    }

    protected abstract double calculateScore();

    public Q[] getQuestions() {
        return questions;
    }

    public D getDifficulty() {
        return difficulty;
    }

    public M getMode() {
        return mode;
    }

    public double getScore() {
        if (score == null) {
            throw new NullPointerException("Call generateStatistics() first!");
        }
        return score;
    }

    public GameStatistics getStatistics() {
        if (statistics == null) {
            throw new NullPointerException("Call generateStatistics() first!");
        }
        return statistics;
    }

    @Override
    public Iterator<Q> iterator() {
        return new QuestionIterator();
    }

    public class QuestionIterator implements Iterator<Q> {
        private int pos = 0;

        public boolean hasNext() {
            return pos < questions.length;
        }

        public Q next() throws NoSuchElementException {
            if (hasNext()) {
                return questions[pos++];
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public String toString() {
        return "Game[" + getQuestions().length + ", " + getDifficulty().toString() + ", " + getMode().toString() + "]";
    }
}
