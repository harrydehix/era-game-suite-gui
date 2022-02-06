package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.GameColors;
import de.harrydehix.eragamesuite.controllers.PageController;
import de.harrydehix.eragamesuite.models.GameSettingsModel;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private JPanel center;
    private JPanel centeredArea;
    private final PageController pageController;
    private final GameSettingsModel gameSettingsModel;

    public GameView(PageController pageController, GameSettingsModel gameSettingsModel) {
        setLayout(new BorderLayout());
        this.gameSettingsModel = gameSettingsModel;
        this.pageController = pageController;

        centeredArea = new JPanel();
        centeredArea.setLayout(new GridBagLayout());
        centeredArea.setBackground(GameColors.DARK_BLUE);

        center = new JPanel();
        center.setBackground(GameColors.DARK_BLUE);

        GridBagConstraints centerConstraint = new GridBagConstraints();
        centerConstraint.fill = GridBagConstraints.HORIZONTAL;
        centerConstraint.anchor = GridBagConstraints.CENTER;
        centeredArea.add(center, centerConstraint);

        add(centeredArea, BorderLayout.CENTER);
    }

    public JPanel getCenterComponent() {
        return center;
    }

    public void changeBackgroundColor(Color c) {
        setBackground(c);
        getCenterComponent().setBackground(c);
        centeredArea.setBackground(c);
    }

    public GameSettingsModel getGameSettingsModel() {
        return gameSettingsModel;
    }

    public PageController getPageController() {
        return pageController;
    }
}
