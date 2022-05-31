package app.controller;

import app.dto.Dice;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class DiceController {
    private final Dice dice;
    private final Button moveButton;

    // class constructor -> sets the moveButton & creates new dice based on ImageView from fxml file
    public DiceController(Group group) {
        moveButton = (Button) group.getChildren().get(0);
        dice = new Dice((ImageView) group.getChildren().get(1));
    }

    // uses Dice class throwTheDice() method to generate number (1-6)
    // sets new dice image
    public int throwTheDice() {
        dice.throwTheDice();

        dice.getDiceTile().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Dice_"+dice.getResultOfThrowingDice()+".png")))); //konkatenacja stringow

        return dice.getResultOfThrowingDice();
    }

    public Button getMoveButton() {
        return moveButton;
    }
}
