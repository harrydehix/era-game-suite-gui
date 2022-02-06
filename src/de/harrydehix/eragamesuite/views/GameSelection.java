package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.GameColors;
import de.harrydehix.eragamesuite.controllers.PageController;
import de.harrydehix.eragamesuite.games.GameDescription;
import de.harrydehix.eragamesuite.models.GameSettingsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GameSelection extends SettingsView {

    public GameSelection(PageController pageController, GameSettingsModel model) {
        super(pageController, model, "Which game do you want to play?", 25, true, true);
        getCenterComponent().add(createGameSelection());
    }

    private Component createGameSelection() {
        JPanel games = new JPanel();
        games.setLayout(new GridLayout(GameDescription.values().length, 1));
        games.setBackground(GameColors.DARK_BLUE);

        ArrayList<GameOption> gameOptions = new ArrayList<>();
        for (GameDescription gameDescription : GameDescription.values()) {
            GameOption option = new GameOption(gameDescription.getGameName(), gameDescription.getGameDescription());
            option.addActionListener((e) -> {
                getGameSettingsModel().setSelectedGame(gameDescription);
            });
            gameOptions.add(option);
            option.addFocusList(gameOptions);
            games.add(option);
            if (getGameSettingsModel().getSelectedGame() == gameDescription) {
                option.enableFocusLook();
            }
        }

        return games;
    }

    @Override
    protected void onGoBackPressed(ActionEvent e) {
        getPageController().switchTo(new MainMenu(getPageController()));
    }

    @Override
    protected void onGoNextPressed(ActionEvent e) {
        if (getGameSettingsModel().getSelectedGame() != null) {
            getPageController().switchTo(new QuestionCountChooser(getPageController(), getGameSettingsModel()));
        }
    }
}
