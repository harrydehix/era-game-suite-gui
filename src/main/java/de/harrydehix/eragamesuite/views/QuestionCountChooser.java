package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.controllers.PageController;
import de.harrydehix.eragamesuite.models.GameSettingsModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class QuestionCountChooser extends SettingsView {
    private final FancySpinner spinner;

    public QuestionCountChooser(PageController pageController, GameSettingsModel model) {
        super(pageController, model, "How many questions do you want?", 25, true, true);

        spinner = new FancySpinner(new SpinnerNumberModel(model.getSelectedQuestionCount(), 1, 99999, 1));
        spinner.getSpinner().addChangeListener((e) -> {
            getGameSettingsModel().setSelectedQuestionCount((int) spinner.getSpinner().getValue());
        });

        getCenterComponent().add(spinner);
    }

    @Override
    protected void onGoBackPressed(ActionEvent e) {
        getPageController().switchTo(new GameSelection(getPageController(), getGameSettingsModel()));
    }

    @Override
    protected void onGoNextPressed(ActionEvent e) {
        getPageController().switchTo(new DifficultySelection(getPageController(), getGameSettingsModel()));
    }
}
