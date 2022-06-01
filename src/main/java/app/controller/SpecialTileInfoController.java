package app.controller;

import app.dto.TileType;
import javafx.scene.Group;
import javafx.scene.control.Label;

public class SpecialTileInfoController {
    private final Group window;
    private Label tileTypeLabel;
    private Label tileInfoLabel;
    private String[] information = {
            "YOU MOVE THREE TILES FORWARD",
            "YOU LOSE ONE GAME TURN",
            "YOU RECEIVE ONE MORE DICE ROLL",
            "YOU MOVE BACKWARDS AS MANY TILES\nAS MANY MUSHROOMS IN YOUR BASKET",
            "YOU LOSE TWO BOLETUS",
            "YOU LOSE ONE BOLETUS AND ONE\nTOADSTOOL",
            "YOU ROLL THE DICE AGAIN AND\nMOVE BACKWARDS"
    };

    public SpecialTileInfoController(Group window) {
        this.window = window;
        tileTypeLabel = (Label) window.getChildren().get(3);
        tileInfoLabel = (Label) window.getChildren().get(4);
    }

    // shows the information about special tile
    public void show(String firstName, TileType tileType) {
        tileTypeLabel.setText(firstName.toUpperCase() + " HAVE ENTERED\nTHE " + tileType + " TILE");
        tileInfoLabel.setText(information[tileType.ordinal() - 2]);
        window.setVisible(true);
    }
}
