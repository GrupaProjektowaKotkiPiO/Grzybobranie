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

        switch (dice.getResultOfThrowingDice()) {
            case 1:
                dice.getDiceTile().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Dice_1.png")))); //konkatenacja stringow
                break;
            case 2:
                dice.getDiceTile().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Dice_2.png"))));
                break;
            case 3:
                dice.getDiceTile().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Dice_3.png"))));
                break;
            case 4:
                dice.getDiceTile().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Dice_4.png"))));
                break;
            case 5:
                dice.getDiceTile().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Dice_5.png"))));
                break;
            default:
                dice.getDiceTile().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("css/images/Dice_6.png"))));
        }

        return dice.getResultOfThrowingDice();
    }

    public Button getMoveButton() {
        return moveButton;
    }
}
