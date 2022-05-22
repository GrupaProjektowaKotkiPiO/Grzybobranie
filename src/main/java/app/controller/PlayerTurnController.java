package app.controller;

import app.dto.Player;
import app.dto.PlayerType;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class PlayerTurnController {
    private Label turnLabel;

    public PlayerTurnController(ObservableList<Node> group4) {
        turnLabel=(Label) group4.get(1);
    }

    public void changePlayerInWindow(Player player) {
        if(player.getType()== PlayerType.PLAYER4) //todo: zmienic to
            turnLabel.setText(player.getFirstName()+" "+player.getLastName());
        else
            turnLabel.setText(player.getFirstName());
    }
}
