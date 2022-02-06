package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.GameColors;
import de.harrydehix.eragamesuite.GameImages;

import javax.swing.*;
import java.awt.*;

public class FancySpinner extends JPanel {
    private final JSpinner spinner;

    public FancySpinner(SpinnerModel model) {
        setBackground(GameColors.DARK_BLUE);
        FlowLayout layout = new FlowLayout();
        layout.setHgap(0);
        layout.setVgap(0);
        setLayout(layout);

        spinner = new JSpinner(model);
        spinner.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        spinner.setBorder(BorderFactory.createEmptyBorder());
        spinner.setPreferredSize(new Dimension(200, 60));
        for (Component component : spinner.getComponents()) {
            if (component.getName() != null && component.getName().endsWith("Button")) {
                spinner.remove(component);
            }
        }
        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) spinner.getEditor();
        spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        spinnerEditor.getTextField().setBackground(GameColors.LIGHTER_DARK_BLUE);
        spinnerEditor.getTextField().setForeground(GameColors.WHITE);

        FancyButton leftButton = new FancyButton("", GameColors.NICE_BLUE, GameColors.BLACK);
        leftButton.setIcon(new ImageIcon(GameImages.LEFT.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        leftButton.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        leftButton.setHorizontalAlignment(JButton.RIGHT);
        leftButton.addActionListener((e) -> {
            try {
                spinner.setValue(spinner.getPreviousValue());
            } catch (IllegalArgumentException ignored) {
                if (spinner.getModel() instanceof SpinnerNumberModel numberModel) {
                    spinner.setValue(numberModel.getMaximum());
                }
                else if (spinner.getModel() instanceof SpinnerListModel listModel) {
                    spinner.setValue(listModel.getList().get(listModel.getList().size() - 1));
                }
            }
        });

        FancyButton rightButton = new FancyButton("", GameColors.NICE_BLUE, GameColors.BLACK);
        rightButton.setIcon(new ImageIcon(GameImages.RIGHT.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        rightButton.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        rightButton.setHorizontalAlignment(JButton.RIGHT);

        rightButton.addActionListener((e) -> {
            try {
                spinner.setValue(spinner.getNextValue());
            } catch (IllegalArgumentException ignored) {
                if (spinner.getModel() instanceof SpinnerNumberModel numberModel) {
                    spinner.setValue(numberModel.getMinimum());
                }
                else if (spinner.getModel() instanceof SpinnerListModel listModel) {
                    spinner.setValue(listModel.getList().get(0));
                }
            }
        });

        add(leftButton);
        add(spinner);
        add(rightButton);
    }

    public JSpinner getSpinner() {
        return spinner;
    }
}
