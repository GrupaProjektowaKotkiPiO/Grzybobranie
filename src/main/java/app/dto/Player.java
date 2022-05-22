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
    private int brownMushrooms;
    private int redMushrooms;

    public Player(PlayerType inputType, String inputFirstName, String inputLastName, ImageView inputPlayerOnBoard) {
        type = inputType;
        firstName = inputFirstName;
        lastName = inputLastName;
        playerOnBoard=inputPlayerOnBoard;
        position=0;
        brownMushrooms =0;
        redMushrooms =0;
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

    public int getBrownMushrooms() {
        return brownMushrooms;
    }

    public void setBrownMushrooms(int brownMushrooms) {
        this.brownMushrooms = brownMushrooms;
    }

    public int getRedMushrooms() {
        return redMushrooms;
    }

    public void setRedMushrooms(int redMushrooms) {
        this.redMushrooms = redMushrooms;
    }
}
