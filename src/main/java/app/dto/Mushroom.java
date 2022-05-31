package app.dto;

import javafx.scene.image.ImageView;

import java.util.Random;

public class Mushroom {
    private final MushroomType type;
    private final ImageView mushroomOnBoard;
    private final int positionOnBoard;

    // class constructor -> sets class tiles & randomise if the mushroom is visible on board
    public Mushroom(MushroomType type, ImageView mushroomOnBoard, int position) {
        this.type = type;
        this.mushroomOnBoard = mushroomOnBoard;
        this.positionOnBoard = position;
        mushroomOnBoard.setVisible((new Random()).nextInt(2) == 1);
    }


    public MushroomType getType() {
        return type;
    }

    public ImageView getMushroomOnBoard() {
        return mushroomOnBoard;
    }

    public int getPositionOnBoard() {
        return positionOnBoard;
    }

}
