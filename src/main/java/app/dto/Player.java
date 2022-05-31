package app.dto;

import javafx.scene.image.ImageView;

import static app.dto.MoveType.*;

public class Player {
    PlayerType type;
    String firstName;
    String lastName;
    private final ImageView playerOnBoard;
    private int position = 0;
    private MoveType moveType = NORMAL;
    private int boletusCounter = 0;
    private int toadstoalCounter = 0;
    private int score = 0;
    private int orderAtEnd = 0;

    // class constructor -> creates the new pklayer with position on board at start tile and score 0
    public Player(PlayerType type, String firstName, String lastName, ImageView playerOnBoard) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerOnBoard = playerOnBoard;
    }

    public PlayerType getType() {
        return type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ImageView getPlayerOnBoard() {
        return playerOnBoard;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }

    public int getBoletusCounter() {
        return boletusCounter;
    }

    public void setBoletusCounter(int boletusCounter) {
        this.boletusCounter = boletusCounter;
    }

    public int getToadstoalCounter() {
        return toadstoalCounter;
    }

    public void setToadstoalCounter(int toadstoalCounter) {
        this.toadstoalCounter = toadstoalCounter;
    }

    public int getOrderAtEnd() {
        return orderAtEnd;
    }

    public void setOrderAtEnd(int orderAtEnd) {
        this.orderAtEnd = orderAtEnd;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
