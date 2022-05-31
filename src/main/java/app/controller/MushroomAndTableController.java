package app.controller;

import app.dto.Mushroom;
import app.dto.MushroomType;
import app.dto.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;


public class MushroomAndTableController {
    private final Mushroom[] mushrooms;
    private final Label[][] scoreTable;
    static private final int NUMBER_OF_MUSHROOMS = 28;
    static private final int TABLE_ROWS = 3;
    static private final int TABLE_COLUMNS = 4;

    // class constructor -> creates all mushrooms and table
    public MushroomAndTableController(Group inputMushrooms, Group inputTable) {
        mushrooms = new Mushroom[NUMBER_OF_MUSHROOMS];
        scoreTable = new Label[TABLE_ROWS][TABLE_COLUMNS];

        // mushrooms initialization
        for (int index = 0, pos = 1; index < NUMBER_OF_MUSHROOMS; index++) {
            if (index == 0 || index == 6 || index == 8 || index == 11 || index == 13 || index == 16 || index == 18 || index == 23 || index == 26) {
                mushrooms[index] = new Mushroom(MushroomType.TOADSTOAL, (ImageView) inputMushrooms.getChildren().get(index), pos);
            }
            else {
                mushrooms[index] = new Mushroom(MushroomType.BOLETUS, (ImageView) inputMushrooms.getChildren().get(index), pos);
            }

            pos++;
            if (pos == 4 || pos == 11 || pos == 17 || pos == 21 || pos == 23 || pos == 26 || pos == 31) {
                pos++;
            }
        }

        // score table initialization
        for (int i = 5, j = 0; i < 17 && j < TABLE_COLUMNS; j++) {
            for (int k = 0; k < TABLE_ROWS; k++, i++) {
                scoreTable[k][j] = (Label) inputTable.getChildren().get(i);
            }
        }
    }

    // collecting the mushroom -> player gets/loses points & mushroom disappear
    public void changeMushroomVisibility(Player player) {
        for (int i = 0; i < NUMBER_OF_MUSHROOMS; i++) {
            if (mushrooms[i].getPositionOnBoard() == player.getPosition() && mushrooms[i].getMushroomOnBoard().isVisible()) {
                mushrooms[i].getMushroomOnBoard().setVisible(false);

                if (mushrooms[i].getType() == MushroomType.BOLETUS) {
                    player.setBoletusCounter(player.getBoletusCounter() + 1);
                }
                else {
                    player.setToadstoalCounter(player.getToadstoalCounter() + 1);
                }

                int index = player.getType().ordinal();
                scoreTable[0][index].setText(Integer.toString(player.getBoletusCounter()));
                scoreTable[1][index].setText(Integer.toString(player.getToadstoalCounter()));
                scoreTable[2][index].setText(Integer.toString(player.getBoletusCounter() - player.getToadstoalCounter()));
                player.setScore(player.getBoletusCounter() - player.getToadstoalCounter()); //todo tutaj wyjasnic

            }
        }
    }
}
