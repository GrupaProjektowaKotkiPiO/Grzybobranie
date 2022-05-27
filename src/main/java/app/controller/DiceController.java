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

    public DiceController(Group group) {
        moveButton = (Button) group.getChildren().get(0);
        dice = new Dice((ImageView) group.getChildren().get(1));
    }

    public int throwTheDice() {
        dice.throwTheDice();
        //ify z klamerkami!

        dice.getDiceTile().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Dice_"+dice.getResultOfThrowingDice()+".png")))); //konkatenacja stringow

        return dice.getResultOfThrowingDice();
    }

    public Button getMoveButton() {
        return moveButton;
    }
}
