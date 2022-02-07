package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.GameColors;
import de.harrydehix.eragamesuite.controllers.PageController;
import de.harrydehix.eragamesuite.models.GameSettingsModel;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private final PageController pageController;
    private final GameSelection gameSelection;

    public MainMenu(PageController pageController) {
        this.pageController = pageController;
        this.gameSelection = new GameSelection(pageController, new GameSettingsModel());

        setLayout(new GridBagLayout());
        setBackground(GameColors.DARK_BLUE);

        JPanel center = new JPanel();
        center.setBackground(GameColors.DARK_BLUE);
        center.setLayout(new GridLayout(2, 1));

        JLabel gameTitle = new JLabel("ERA GAME SUITE");
        gameTitle.setForeground(GameColors.WHITE);
        gameTitle.setHorizontalAlignment(JLabel.CENTER);
        gameTitle.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        gameTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));

        center.add(gameTitle);

        JPanel buttonBar = new JPanel();
        buttonBar.setBackground(GameColors.DARK_BLUE);

        FancyButton playButton = new FancyButton("Play", GameColors.DARK_BLUE, GameColors.NICE_GREEN, GameColors.NICE_GREEN, GameColors.WHITE, GameColors.BLACK, GameColors.WHITE);
        playButton.addActionListener((e) -> {
            pageController.switchTo(gameSelection);
        });
        FancyButton highscoreButton = new FancyButton("Highscores", GameColors.DARK_BLUE, GameColors.NICE_PINK, GameColors.NICE_PINK, GameColors.WHITE, GameColors.BLACK, GameColors.WHITE);

        buttonBar.add(playButton);
        buttonBar.add(highscoreButton);

        center.add(buttonBar);

        GridBagConstraints centerConstraint = new GridBagConstraints();
        centerConstraint.fill = GridBagConstraints.HORIZONTAL;
        centerConstraint.anchor = GridBagConstraints.CENTER;
        add(center, centerConstraint);
    }
}
