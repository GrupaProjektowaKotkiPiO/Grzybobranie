package app.dto;

import javafx.scene.image.ImageView;
import static app.dto.MoveType.*;

public class Player {
    PlayerType type;
    String firstName;
    String lastName;
    private ImageView playerOnBoard;
    private int position;
    private MoveType moveType;
    private int boletusCounter;
    private int toadstoalCounter;
    private int bonus;

    public Player(PlayerType inputType, String inputFirstName, String inputLastName, ImageView inputPlayerOnBoard) {
        type = inputType;
        firstName = inputFirstName;
        lastName = inputLastName;
        playerOnBoard=inputPlayerOnBoard;
        position=0;
        boletusCounter=0;
        toadstoalCounter=0;
        bonus=0;
        moveType = NORMAL;
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
    public MoveType getMoveType() {return moveType;}

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

    public int getBonus() { return bonus; }

    public void setBonus(int bonus) { this.bonus = bonus; }
}
