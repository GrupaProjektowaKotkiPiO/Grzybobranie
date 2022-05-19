package app.dto;

import javafx.scene.image.ImageView;

public class Player {
    PlayerType type;
    String firstName;
    String lastName;
    private ImageView playerOnBoard;
    private int position;

    public Player(PlayerType inputType, String inputFirstName, String inputLastName, ImageView inputPlayerOnBoard) {
        type = inputType;
        firstName = inputFirstName;
        lastName = inputLastName;
        playerOnBoard=inputPlayerOnBoard;
        position=0;
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
}
