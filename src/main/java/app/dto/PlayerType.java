package app.dto;

public enum PlayerType {
    PLAYER1(0),
    PLAYER2(1),
    PLAYER3(2),
    PLAYER4(3);

    private final int index;

    PlayerType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
