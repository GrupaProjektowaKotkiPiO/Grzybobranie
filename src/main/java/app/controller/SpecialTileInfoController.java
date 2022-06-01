package app.controller;

import app.dto.TileType;
import javafx.scene.Group;
import javafx.scene.control.Label;

public class SpecialTileInfoController {
    private final Group window;
    private Label tileTypeLabel;
    private Label tileInfoLabel;
    private String[] information = {
            "YOU MOVE 3 TILES FORWARD",
            "YOU LOSE 1 GAME TURN",
            "YOU RECEIVE ONE MORE DICE ROLL",
            "YOU MOVE BACKWARDS AS MANY TILES\nAS MANY MUSHROOMS IN YOUR BASKET",
            "YOU LOSE 2 BOLETUS",
            "YOU LOSE 1 BOLETUS AND 1 TOADSTOOL",
            "YOU ROLL THE DICE AGAIN AND\nMOVE BACKWARDS"
    };

    public SpecialTileInfoController(Group window) {
        this.window = window;
        tileTypeLabel = (Label) window.getChildren().get(3);
        tileInfoLabel = (Label) window.getChildren().get(4);
    }

    // shows the information about special tile
    public void show(TileType tileType) {
        tileTypeLabel.setText("YOU HAVE ENTERED THE " + tileType + " TILE");
        tileInfoLabel.setText(information[tileType.ordinal() - 2]);
        window.setVisible(true);
    }
}
