package de.harrydehix.eragamesuite.views;

import java.awt.*;

public class AnimationController {
    private final Component component, mainContainer;
    private Thread animationThread;

    public AnimationController(Component component, Component mainContainer) {
        this.component = component;
        this.mainContainer = mainContainer;
    }

    public void animateBackground(Color from, Color to, int steps, long delay, long timePerStep) {
        if (animationThread != null && animationThread.isAlive()) {
            animationThread.interrupt();
        }
        animationThread = new Thread(() -> {
            component.setBackground(from);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                return;
            }
            double stepSize = 1D / steps;
            double ratio = 1;
            while (ratio > 0) {
                component.setBackground(mixColors(from, to, ratio));
                ratio -= stepSize;
                try {
                    Thread.sleep(timePerStep);
                } catch (InterruptedException e) {
                    return;
                }
            }
            component.setBackground(to);
        });
        animationThread.start();
    }

    public void animateForeground(Color from, Color to, int steps, long delay, long timePerStep) {
        if (animationThread != null && animationThread.isAlive()) {
            animationThread.interrupt();
        }
        animationThread = new Thread(() -> {
            component.setForeground(from);
            mainContainer.repaint();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                return;
            }
            double stepSize = 1D / steps;
            double ratio = 1;
            while (ratio > 0) {
                component.setForeground(mixColors(from, to, ratio));
                mainContainer.repaint();
                ratio -= stepSize;
                try {
                    Thread.sleep(timePerStep);
                } catch (InterruptedException e) {
                    return;
                }
            }
            component.setForeground(to);
            mainContainer.repaint();
        });
        animationThread.start();
    }

    private static Color mixColors(Color c0, Color c1, double ratio) {
        double weight0 = ratio;
        double weight1 = 1 - ratio;

        double r = weight0 * c0.getRed() + weight1 * c1.getRed();
        double g = weight0 * c0.getGreen() + weight1 * c1.getGreen();
        double b = weight0 * c0.getBlue() + weight1 * c1.getBlue();
        double a = weight0 * c0.getAlpha() + weight1 * c1.getAlpha();

        return new Color((int) r, (int) g, (int) b, (int) a);
    }
}
