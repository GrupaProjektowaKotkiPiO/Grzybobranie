package app.controller;

import app.dto.Player;
import app.dto.PlayerType;
import javafx.scene.Group;
import javafx.scene.control.Label;

public class FinishGameWindowController {
    private Player winner;
    private Group window;

    public FinishGameWindowController(Group window) {
        this.winner = null;
        this.window = window;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void show() {
        ((Label)(window.getChildren().get(5))).setText(winner.getFirstName()+" win!");
        window.setVisible(true);
    }
}
