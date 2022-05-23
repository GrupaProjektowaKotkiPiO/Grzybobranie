package app.dto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class Tile {
    private TileType type;
    private double basketX;
    private double basketY;
    private ImageView imageView;

    public Tile(TileType inputType, double inputBasketX, double inputBasketY) {
        type=inputType;
        basketX=inputBasketX;
        basketY=inputBasketY;
        imageView= new ImageView();
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TileType getType() {
        return type;
    }

    public double getBasketX() {
        return basketX;
    }

    public double getBasketY() {
        return basketY;
    }
}
