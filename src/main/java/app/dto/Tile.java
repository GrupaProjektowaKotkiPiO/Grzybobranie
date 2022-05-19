package app.dto;

public class Tile {
    private TileType type;
    private double basketX;
    private double basketY;

    public Tile(TileType inputType, double inputBasketX, double inputBasketY) {
        type=inputType;
        basketX=inputBasketX;
        basketY=inputBasketY;
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
