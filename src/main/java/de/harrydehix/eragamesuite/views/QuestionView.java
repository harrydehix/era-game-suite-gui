package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.GameColors;
import de.harrydehix.eragamesuite.controllers.PageController;
import de.harrydehix.eragamesuite.games.Question;
import de.harrydehix.eragamesuite.games.ScoreConsumer;
import de.harrydehix.eragamesuite.models.GameSettingsModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Iterator;

public class QuestionView extends GameView {
    private final JLabel questionLabel, answerFeedbackLabel;
    private final JTextField answerInput;
    private final JProgressBar gameProgressBar;
    private final Iterator<? extends Question> questionIterator;
    private Question currentQuestion;

    private AnimationController textFieldAnimator, feedbackAnimator;

    public QuestionView(PageController pageController, GameSettingsModel gameSettingsModel) {
        super(pageController, gameSettingsModel);
        questionIterator = getGameSettingsModel().getGame().iterator();

        gameProgressBar = new JProgressBar(0, 0, getGameSettingsModel().getSelectedQuestionCount());
        gameProgressBar.setBorder(BorderFactory.createEmptyBorder());
        gameProgressBar.setBackground(GameColors.LIGHTER_DARK_BLUE);
        gameProgressBar.setForeground(GameColors.NICE_GREEN);
        gameProgressBar.setBorderPainted(false);
        add(gameProgressBar, BorderLayout.PAGE_START);

        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        questionLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
        questionLabel.setForeground(GameColors.WHITE);

        JPanel answerContainer = new JPanel();
        answerContainer.setLayout(new GridLayout(2, 1));
        answerContainer.setBackground(GameColors.TRANSPARENT);

        answerInput = new JTextField();
        textFieldAnimator = new AnimationController(answerInput, this);
        answerInput.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        answerInput.setBorder(BorderFactory.createEmptyBorder());
        answerInput.setMaximumSize(new Dimension(275, 55));
        answerInput.setPreferredSize(new Dimension(275, 55));
        answerInput.setHorizontalAlignment(JTextField.CENTER);
        answerInput.setBackground(GameColors.LIGHTER_DARK_BLUE);
        answerInput.setForeground(GameColors.WHITE);

        answerFeedbackLabel = new JLabel();
        answerFeedbackLabel.setHorizontalAlignment(JLabel.CENTER);
        answerFeedbackLabel.setForeground(GameColors.TRANSPARENT);
        feedbackAnimator = new AnimationController(answerFeedbackLabel, this);

        answerInput.addActionListener((e) -> {
            boolean correct = currentQuestion.solve(answerInput.getText());
            answerInput.setText("");
            if (!correct) {
                answerFeedbackLabel.setText("That's wrong! " + currentQuestion.getAnswer() + " would have been right!");
                textFieldAnimator.animateBackground(GameColors.CHILL_RED, GameColors.LIGHTER_DARK_BLUE, 100, 0, 5);
                feedbackAnimator.animateForeground(GameColors.NICE_ORANGE, GameColors.DARK_BLUE, 100, 1500, 5);
            }
            else {
                textFieldAnimator.animateBackground(GameColors.CHILL_GREEN, GameColors.LIGHTER_DARK_BLUE, 100, 0, 5);
            }
            if (!next(false)) {
                onGameFinished();
            }
        });
        answerInput.addPropertyChangeListener((e) -> {
            repaint();
        });

        answerContainer.add(answerInput);
        answerContainer.add(answerFeedbackLabel);

        getCenterComponent().setLayout(new GridLayout(2, 1));
        getCenterComponent().add(questionLabel);
        getCenterComponent().add(answerContainer);
        next(true);
    }

    private void onGameFinished() {
        System.out.println("Finished!");
        getGameSettingsModel().getGame().generateStatistics();
        System.out.println("Generated stats");
        try {
            ScoreConsumer.getInstance().consume(getGameSettingsModel().getGame().getStatistics());
            System.out.println("Consumed new score");
        } catch (IOException e) {
            e.printStackTrace();
        }
        getPageController().switchTo(new GameEndView(getPageController(), getGameSettingsModel()));
    }

    public boolean next(boolean first) {
        if (questionIterator.hasNext()) {
            currentQuestion = questionIterator.next();
            questionLabel.setText(currentQuestion.getQuestion());
            if (!first) {
                gameProgressBar.setValue(gameProgressBar.getValue() + 1);
            }
            currentQuestion.startQuestionTimer();
            return true;
        }
        else {
            gameProgressBar.setValue(gameProgressBar.getMaximum());
            return false;
        }
    }
}
