package app.controller;

import app.dto.Player;
import app.dto.PlayerType;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerTurnController {
    private Label turnLabel;
    private ImageView turnImage;

    public PlayerTurnController(Group group4) {
        turnLabel=(Label) group4.getChildren().get(2);
        turnImage=(ImageView) group4.getChildren().get(4);
    }

    public void changePlayerInWindow(Player player) {
        turnLabel.setText(player.getFirstName());

        switch (player.getType()) {
            case PLAYER1:
                turnImage.setImage(new Image(getClass().getResourceAsStream("css/images/Basket_1.png")));
                break;
            case PLAYER2:
                turnImage.setImage(new Image(getClass().getResourceAsStream("css/images/Basket_2.png")));
                break;
            case PLAYER3:
                turnImage.setImage(new Image(getClass().getResourceAsStream("css/images/Basket_3.png")));
                break;
            default:
                turnImage.setImage(new Image(getClass().getResourceAsStream("css/images/Basket_4.png")));
        }

    }
}
