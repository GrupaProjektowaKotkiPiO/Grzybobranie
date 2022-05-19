package app.controller;

import app.dto.Dice;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DiceController {
    private Dice dice;
    private Button moveButton;

    public DiceController(ObservableList<Node> group) {
        moveButton = (Button) group.get(0);
        dice = new Dice((ImageView) group.get(1));
    }

    public int throwTheDice() {
        dice.throwTheDice();

        switch (dice.getResultOfThrowingDice()) {
            case 1:
                dice.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_1.png")));
                break;
            case 2:
                dice.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_2.png")));
                break;
            case 3:
                dice.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_3.png")));
                break;
            case 4:
                dice.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_4.png")));
                break;
            case 5:
                dice.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_5.png")));
                break;
            default:
                dice.getDiceTile().setImage(new Image(getClass().getResourceAsStream("css/images/Dice_6.png")));
        }

        return dice.getResultOfThrowingDice();
    }

    public Button getMoveButton() { return moveButton; }
}
