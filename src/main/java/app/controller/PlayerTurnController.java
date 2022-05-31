package app.controller;

import app.dto.Player;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class PlayerTurnController {
    private final Label turnLabel;
    private final ImageView turnImage;

    // class constructor -> sets label & image based on fxml file
    public PlayerTurnController(Group group4) {
        turnLabel = (Label) group4.getChildren().get(2);
        turnImage = (ImageView) group4.getChildren().get(4);
    }

    // changes the current player name & image (player whose turn it is)
    public void changePlayerInWindow(Player player) {
        turnLabel.setText(player.getFirstName());
        turnImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Basket_" + (player.getType().ordinal() + 1)  + ".png"))));
    }
}
