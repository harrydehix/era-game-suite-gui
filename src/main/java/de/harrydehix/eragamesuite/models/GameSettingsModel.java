package de.harrydehix.eragamesuite.models;

import de.harrydehix.eragamesuite.games.Difficulty;
import de.harrydehix.eragamesuite.games.Game;
import de.harrydehix.eragamesuite.games.GameDescription;
import de.harrydehix.eragamesuite.games.Mode;

public class GameSettingsModel {
    private GameDescription selectedGame = GameDescription.BIN_HEX;
    private int selectedQuestionCount = 10;
    private Difficulty selectedDifficulty = null;
    private Mode selectedMode = null;
    private Game game = null;

    public GameDescription getSelectedGame() {
        return selectedGame;
    }

    public void setSelectedGame(GameDescription selectedGame) {
        if (selectedGame != this.selectedGame) {
            selectedDifficulty = null;
            selectedMode = null;
        }
        this.selectedGame = selectedGame;
    }

    public int getSelectedQuestionCount() {
        return selectedQuestionCount;
    }

    public void setSelectedQuestionCount(int selectedQuestionCount) {
        this.selectedQuestionCount = selectedQuestionCount;
    }

    public Difficulty getSelectedDifficulty() {
        return selectedDifficulty;
    }

    public void setSelectedDifficulty(Difficulty selectedDifficulty) {
        this.selectedDifficulty = selectedDifficulty;
    }

    public void setSelectedMode(Mode selectedMode) {
        this.selectedMode = selectedMode;
    }

    public Mode getSelectedMode() {
        return selectedMode;
    }

    public boolean createGame() {
        this.game = getSelectedGame().createInstance(getSelectedQuestionCount(), getSelectedDifficulty(), getSelectedMode());
        return this.game != null;
    }

    public Game getGame() {
        return game;
    }

    public void resetGame() {
        game = null;
    }

    public void reset() {
        selectedGame = GameDescription.BIN_HEX;
        selectedQuestionCount = 10;
        selectedDifficulty = null;
        selectedMode = null;
        game = null;
    }
}
