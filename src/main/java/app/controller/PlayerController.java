package app.controller;

import app.dto.Player;
import app.dto.PlayerType;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class PlayerController {
    private Player[] players;

    public PlayerController(ObservableList<Node> group) {
        players=new Player[4];
        players[0]=new Player(PlayerType.PLAYER1,"Szymon","Jakubaszek",(ImageView)group.get(3));
        players[1]=new Player(PlayerType.PLAYER2,"Daria","Glińska",(ImageView)group.get(2));
        players[2]=new Player(PlayerType.PLAYER3,"Maciej","Sierzputowski",(ImageView)group.get(1));
        players[3]=new Player(PlayerType.PLAYER4,"Pani","Doktor",(ImageView)group.get(0));
    }

    public void moveThePlayer(PlayerType type, TileController tileController) {
        switch (type) {
            case PLAYER1:
                setPositionOnBoard(players[0], tileController);
                break;
            case PLAYER2:
                setPositionOnBoard(players[1], tileController);
                break;
            case PLAYER3:
                setPositionOnBoard(players[2], tileController);
                break;
            default:
                setPositionOnBoard(players[3], tileController);
        }
    }

    private void setPositionOnBoard(Player player, TileController tileController) {
        player.getPlayerOnBoard().setLayoutX(tileController.getBoard()[player.getPosition()].getBasketX()-46);
        player.getPlayerOnBoard().setLayoutY(tileController.getBoard()[player.getPosition()].getBasketY()-43.5);
    }

    public Player[] getPlayers() { return players; }
}
