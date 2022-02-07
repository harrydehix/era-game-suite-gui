package de.harrydehix.eragamesuite;

import de.harrydehix.eragamesuite.controllers.PageController;
import de.harrydehix.eragamesuite.views.MainMenu;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    private Component currentlySelectedView;
    private final PageController pageController = new PageController();

    private Application() {
        pageController.setApplication(this);

        setLayout(new BorderLayout());
        setSize(600, 600);
        setTitle("ERA Game Suite GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        switchTo(new MainMenu(pageController));

        JPanel bottom = new JPanel();
        bottom.setBackground(GameColors.LIGHTER_DARK_BLUE);
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel version = new JLabel("v0.0.1");
        version.setForeground(Color.WHITE);
        bottom.add(version);

        add(bottom, BorderLayout.PAGE_END);
        setVisible(true);
    }

    public void switchTo(Component component) {
        if (currentlySelectedView != null) {
            remove(currentlySelectedView);
        }
        add(component, BorderLayout.CENTER);
        currentlySelectedView = component;
        revalidate();
        repaint();
    }


    public static void main(String[] args) {
        new Application();
    }
}
