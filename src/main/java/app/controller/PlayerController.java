package app.controller;

import app.dto.Player;
import app.dto.PlayerType;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class PlayerController {
    private final Player[] players;

    // class constructor -> creates Players
    public PlayerController(Group group) {
        players = new Player[4];
        players[0] = new Player(PlayerType.PLAYER1, "Szymon", "Jakubaszek", (ImageView) group.getChildren().get(3));
        players[1] = new Player(PlayerType.PLAYER2, "Daria", "Glińska", (ImageView) group.getChildren().get(2));
        players[2] = new Player(PlayerType.PLAYER3, "Maciej", "Sierzputowski", (ImageView) group.getChildren().get(1));
        players[3] = new Player(PlayerType.PLAYER4, "Pani", "Doktor", (ImageView) group.getChildren().get(0));
    }

    // changes player position on board
    public void moveThePlayer(PlayerType type, TileController tileController) {
        setPositionOnBoard(players[type.getIndex()], tileController);
    }

    // sets the new player position
    private void setPositionOnBoard(Player player, TileController tileController) {
        player.getPlayerOnBoard().setLayoutX(tileController.getBoard()[player.getPosition()].getBasketX() - 46);
        player.getPlayerOnBoard().setLayoutY(tileController.getBoard()[player.getPosition()].getBasketY() - 43.5);
    }

    public Player[] getPlayers() {
        return players;
    }
}
