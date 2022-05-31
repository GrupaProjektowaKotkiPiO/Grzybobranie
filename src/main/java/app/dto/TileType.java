package app.dto;

public enum TileType {
    NORMAL(0),
    START(1),
    RABBIT(2),
    MOUNTAIN(3),
    WATERFALL(4),
    HILL(5),
    VALLEY(6),
    THUNDER(7),
    BOAR(8),
    FINISH(9);


    private final int index;

    TileType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
