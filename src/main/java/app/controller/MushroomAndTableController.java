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


    public MushroomAndTableController(Group inputMushrooms, Group inputTable) {
        mushrooms = new Mushroom[NUMBER_OF_MUSHROOMS];
        scoreTable = new Label[TABLE_ROWS][TABLE_COLUMNS];

        for (int i = 0, j = 1; i < NUMBER_OF_MUSHROOMS; i++) {
            if (i == 0 || i == 6 || i == 8 || i == 11 || i == 13 || i == 16 || i == 18 || i == 23 || i == 26)
                mushrooms[i] = new Mushroom(MushroomType.TOADSTOAL, (ImageView) inputMushrooms.getChildren().get(i), j);
            else
                mushrooms[i] = new Mushroom(MushroomType.BOLETUS, (ImageView) inputMushrooms.getChildren().get(i), j);

            j += 1;
            if (j == 4 || j == 11 || j == 17 || j == 21 || j == 23 || j == 26 || j == 31)
                j += 1;
        }

        for (int i = 5, j = 0; i < 17 && j < TABLE_COLUMNS; j++) {
            for (int k = 0; k < TABLE_ROWS; k++, i++)
                scoreTable[k][j] = (Label) inputTable.getChildren().get(i);
        }
    }

    public void changeMushroomVisibility(Player player) {
        for (int i = 0; i < NUMBER_OF_MUSHROOMS; i++) {
            if (mushrooms[i].getPositionOnBoard() == player.getPosition() && mushrooms[i].getMushroomOnBoard().isVisible()) {
                mushrooms[i].getMushroomOnBoard().setVisible(false);

                if (mushrooms[i].getType() == MushroomType.BOLETUS)
                    player.setBoletusCounter(player.getBoletusCounter() + 1);
                else
                    player.setToadstoalCounter(player.getToadstoalCounter() + 1);

                int j;
                switch (player.getType()) {
                    case PLAYER1:
                        j = 0;
                        break;
                    case PLAYER2:
                        j = 1;// umiejscowienie grzybkow i enum
                        break;
                    case PLAYER3:
                        j = 2;
                        break;
                    default:
                        j = 3;
                        break;
                }
                scoreTable[0][j].setText(Integer.toString(player.getBoletusCounter()));
                scoreTable[1][j].setText(Integer.toString(player.getToadstoalCounter()));
                scoreTable[2][j].setText(Integer.toString(player.getBoletusCounter() - player.getToadstoalCounter()));
                player.setScore(player.getBoletusCounter() - player.getToadstoalCounter()); //tutaj wyjasnic

            }
        }
    }
}
