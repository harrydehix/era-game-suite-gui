package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.GameColors;
import de.harrydehix.eragamesuite.controllers.PageController;
import de.harrydehix.eragamesuite.models.GameSettingsModel;

import javax.swing.*;
import java.awt.*;

public class CounterView extends GameView {
    private int counterStart = 3;
    private final String endMessage = "GOO!";
    private final long delayMillis = 1000;
    private final JLabel counter;
    private QuestionView questionView;

    public CounterView(PageController pageController, GameSettingsModel gameSettingsModel) {
        super(pageController, gameSettingsModel);
        this.counterStart = counterStart;

        this.counter = new JLabel(String.valueOf(counterStart));
        counter.setForeground(GameColors.WHITE);
        counter.setFont(new Font("Sans Serif", Font.BOLD, 55));
        startCounter();
        getCenterComponent().add(counter);
    }

    public synchronized void startCounter() {
        new Thread(() -> {
            for (int i = counterStart; i >= 0; i--) {
                if (i == 0) {
                    counter.setText(endMessage);
                }
                else {
                    counter.setText(String.valueOf(i));
                }
                sleep(delayMillis);
            }
            onCounterFinished();
        }).start();
        getGameSettingsModel().createGame();
        questionView = new QuestionView(getPageController(), getGameSettingsModel());
    }

    public void onCounterFinished() {
        System.out.println("Starting game...");
        System.out.println(getGameSettingsModel().getGame());
        getPageController().switchTo(questionView);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public JLabel getCounter() {
        return counter;
    }
}
