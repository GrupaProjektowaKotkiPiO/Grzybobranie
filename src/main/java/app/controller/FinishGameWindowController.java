package app.controller;

import app.dto.Player;
import app.dto.PlayerType;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
        ((ImageView)(window.getChildren().get(4))).setImage(winner.getPlayerOnBoard().getImage());
        ((Label)(window.getChildren().get(5))).setText(winner.getFirstName()+" win!");
        window.setVisible(true);
    }
}
