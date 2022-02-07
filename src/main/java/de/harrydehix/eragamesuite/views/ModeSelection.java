package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.controllers.PageController;
import de.harrydehix.eragamesuite.games.Mode;
import de.harrydehix.eragamesuite.models.GameSettingsModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ModeSelection extends SettingsView {
    private final FancySpinner spinner;

    public ModeSelection(PageController pageController, GameSettingsModel model) {
        super(pageController, model, "Choose a game mode", 25, true, true);

        spinner = new FancySpinner(new SpinnerListModel(getGameSettingsModel().getSelectedGame().getModes()));
        if (getGameSettingsModel().getSelectedMode() != null) {
            spinner.getSpinner().setValue(getGameSettingsModel().getSelectedMode());
        }
        else {
            getGameSettingsModel().setSelectedMode((Mode) spinner.getSpinner().getValue());
        }
        spinner.getSpinner().addChangeListener((e) -> {
            getGameSettingsModel().setSelectedMode((Mode) spinner.getSpinner().getValue());
        });

        getCenterComponent().add(spinner);
    }

    @Override
    protected void onGoBackPressed(ActionEvent e) {
        getPageController().switchTo(new DifficultySelection(getPageController(), getGameSettingsModel()));
    }

    @Override
    protected void onGoNextPressed(ActionEvent e) {
        getPageController().switchTo(new CounterView(getPageController(), getGameSettingsModel()));
    }
}
