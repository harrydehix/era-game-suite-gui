package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.controllers.PageController;
import de.harrydehix.eragamesuite.games.Difficulty;
import de.harrydehix.eragamesuite.models.GameSettingsModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DifficultySelection extends SettingsView {
    private final FancySpinner spinner;

    public DifficultySelection(PageController pageController, GameSettingsModel model) {
        super(pageController, model, "How tough do you want it?", 25, true, true);

        spinner = new FancySpinner(new SpinnerListModel(getGameSettingsModel().getSelectedGame().getDifficulties()));
        if (getGameSettingsModel().getSelectedDifficulty() != null) {
            spinner.getSpinner().setValue(getGameSettingsModel().getSelectedDifficulty());
        }
        else {
            getGameSettingsModel().setSelectedDifficulty((Difficulty) spinner.getSpinner().getValue());
        }
        spinner.getSpinner().addChangeListener((e) -> {
            getGameSettingsModel().setSelectedDifficulty((Difficulty) spinner.getSpinner().getValue());
        });

        getCenterComponent().add(spinner);
    }

    @Override
    protected void onGoBackPressed(ActionEvent e) {
        getPageController().switchTo(new QuestionCountChooser(getPageController(), getGameSettingsModel()));
    }

    @Override
    protected void onGoNextPressed(ActionEvent e) {
        getPageController().switchTo(new ModeSelection(getPageController(), getGameSettingsModel()));
    }
}
