package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.GameColors;
import de.harrydehix.eragamesuite.GameFonts;
import de.harrydehix.eragamesuite.GameImages;
import de.harrydehix.eragamesuite.controllers.PageController;
import de.harrydehix.eragamesuite.models.GameSettingsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class SettingsView extends GameView {
    private final FancyButton goBackButton, goNextButton;

    public SettingsView(PageController pageController, GameSettingsModel gameSettingsModel, String question, int fontSize, boolean showGoBackButton, boolean showGoNextButton) {
        super(pageController, gameSettingsModel);
        getCenterComponent().setLayout(new GridLayout(2, 1));

        JLabel questionLabel = new JLabel(question);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        questionLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize));

        getCenterComponent().add(questionLabel);

        JPanel bottomBar = new JPanel();
        bottomBar.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        bottomBar.setBackground(GameColors.DARK_BLUE);
        bottomBar.setLayout(new BorderLayout());

        goBackButton = new FancyButton("", GameColors.NICE_PINK, GameColors.WHITE);
        goBackButton.setIcon(new ImageIcon(GameImages.GO_BACK.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        goBackButton.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        goBackButton.setFont(GameFonts.BUTTON_FONT_BOLD);
        goBackButton.setHorizontalAlignment(JButton.LEFT);
        goBackButton.addActionListener(this::onGoBackPressed);
        goBackButton.setVisible(showGoBackButton);
        bottomBar.add(goBackButton, BorderLayout.LINE_START);

        goNextButton = new FancyButton("", GameColors.NICE_GREEN, GameColors.BLACK);
        goNextButton.setIcon(new ImageIcon(GameImages.GO_NEXT.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        goNextButton.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        goNextButton.setHorizontalAlignment(JButton.RIGHT);
        goNextButton.addActionListener(this::onGoNextPressed);
        goNextButton.setVisible(showGoNextButton);
        bottomBar.add(goNextButton, BorderLayout.LINE_END);

        add(bottomBar, BorderLayout.PAGE_END);
    }

    protected abstract void onGoBackPressed(ActionEvent e);

    protected abstract void onGoNextPressed(ActionEvent e);

    public void hideGoNextButton() {
        goNextButton.setVisible(false);
    }

    public void showGoNextButton() {
        goNextButton.setVisible(true);
    }

    public void hideGoBackButton() {
        goNextButton.setVisible(false);
    }

    public void showGoBackButton() {
        goNextButton.setVisible(true);
    }

    public FancyButton getGoBackButton() {
        return goBackButton;
    }

    public FancyButton getGoNextButton() {
        return goNextButton;
    }
}
