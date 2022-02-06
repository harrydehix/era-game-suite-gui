package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.GameColors;

import javax.swing.*;
import java.awt.*;

public class GameOption extends FlexibleButton {
    public GameOption(String gameName, String gameDescription) {
        super("", GameColors.LIGHTER_DARK_BLUE, GameColors.WHITE,
                GameColors.LIGHTER_DARK_BLUE, GameColors.WHITE,
                GameColors.LIGHTER_DARK_BLUE, GameColors.WHITE,
                GameColors.LIGHT_DARK_BLUE, GameColors.WHITE);
        setLayout(new BorderLayout());
        setBackground(GameColors.LIGHTER_DARK_BLUE);

        JLabel title = new JLabel(gameName);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        title.setForeground(GameColors.WHITE);
        JLabel description = new JLabel(gameDescription);
        description.setForeground(GameColors.WHITE);

        add(title, BorderLayout.LINE_START);
        add(description, BorderLayout.LINE_END);
    }
}
