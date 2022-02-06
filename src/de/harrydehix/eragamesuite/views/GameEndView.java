package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.GameColors;
import de.harrydehix.eragamesuite.controllers.PageController;
import de.harrydehix.eragamesuite.models.GameSettingsModel;

import javax.swing.*;
import java.awt.*;

public class GameEndView extends GameView {
    public GameEndView(PageController pageController, GameSettingsModel gameSettingsModel) {
        super(pageController, gameSettingsModel);

        getCenterComponent().setLayout(new GridLayout(3, 1));

        // JLabel highscoreLabel = new JLabel(); TODO

        JLabel score = new JLabel(String.format("%.2f ERA-POINTS", getGameSettingsModel().getGame().getScore()));
        score.setFont(new Font(Font.SERIF, Font.ITALIC, 55));
        score.setForeground(GameColors.WHITE);
        score.setHorizontalAlignment(JLabel.CENTER);

        getCenterComponent().add(score);

        JPanel stats = new JPanel();
        stats.setBackground(GameColors.DARK_BLUE);

        JLabel timeSum = new JLabel(String.format("You took %.1fs.", getGameSettingsModel().getGame().getStatistics().getAnswerTime()));
        timeSum.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        timeSum.setHorizontalAlignment(JLabel.CENTER);
        timeSum.setForeground(GameColors.WHITE);

        JLabel timePerQuestion = new JLabel(String.format("That's %.1fs per question.", getGameSettingsModel().getGame().getStatistics().getAnswerTimePerQuestion()));
        timePerQuestion.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        timePerQuestion.setHorizontalAlignment(JLabel.CENTER);
        timePerQuestion.setForeground(GameColors.WHITE);

        JLabel quote = new JLabel(String.format("You answered %.2f%s correctly.", getGameSettingsModel().getGame().getStatistics().getCorrectAnswerQuote() * 100, "%"));
        quote.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        quote.setHorizontalAlignment(JLabel.CENTER);
        quote.setForeground(GameColors.WHITE);

        stats.add(timeSum);
        stats.add(timePerQuestion);
        stats.add(quote);
        getCenterComponent().add(stats);

        JPanel buttonArea = new JPanel();
        buttonArea.setBackground(GameColors.DARK_BLUE);
        buttonArea.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        FancyButton mainMenuButton = new FancyButton("Main Menu", GameColors.NICE_ORANGE, GameColors.BLACK, GameColors.BLACK, GameColors.NICE_ORANGE, GameColors.BLACK, GameColors.NICE_ORANGE);
        mainMenuButton.addActionListener((e) -> {
            getGameSettingsModel().reset();
            getPageController().switchTo(new MainMenu(getPageController()));
        });

        FancyButton playAgainButton = new FancyButton("Play again", GameColors.NICE_BLUE, GameColors.WHITE, GameColors.BLACK, GameColors.NICE_BLUE, GameColors.BLACK, GameColors.NICE_BLUE);
        playAgainButton.addActionListener((e) -> {
            getGameSettingsModel().resetGame();
            getPageController().switchTo(new CounterView(getPageController(), getGameSettingsModel()));
        });

        FancyButton highscoresButton = new FancyButton("Highscores", GameColors.NICE_PINK, GameColors.BLACK, GameColors.BLACK, GameColors.NICE_PINK, GameColors.BLACK, GameColors.NICE_PINK);
        buttonArea.add(mainMenuButton);
        buttonArea.add(playAgainButton);
        buttonArea.add(highscoresButton);

        getCenterComponent().add(buttonArea);
    }
}
