package app.dto;

public class Tile {
    private final TileType type;
    private final double basketX;
    private final double basketY;

    // class constructor -> creates new tile of specific type & sets basket cords
    public Tile(TileType inputType, double inputBasketX, double inputBasketY) {
        type = inputType;
        basketX = inputBasketX;
        basketY = inputBasketY;
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
