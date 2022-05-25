package app.controller;

import app.dto.Player;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class FinishGameWindowController {
    private Player[] players;
    private Player firstPlacePlayer;
    private Player secondPlacePlayer;
    private Player thirdPlacePlayer;
    private final Group window;

    public FinishGameWindowController(Group window) {
        this.window = window;
        players = new Player[4];
    }

    public void show() {
        setPositionsOnPodium(players);
        window.setVisible(true);
    }

    public void setPositionsOnPodium(Player[] player) {

        for (int i = 0; i < player.length; i++) {   //bubble sort
            for (int j = 0; j < player.length - i - 1; j++) {
                int result1 = player[j].getScore();
                int result2 = player[j + 1].getScore();
                System.out.println(result1 + " " + result2);
                if (result1 < result2 || (result1 == result2 && player[j].getOrderAtEnd() < player[j + 1].getOrderAtEnd())) {
                    Player temp = player[j];
                    player[j] = player[j + 1];
                    player[j + 1] = temp;
                }
            }
        }
        firstPlacePlayer = player[0];
        secondPlacePlayer = player[1];
        thirdPlacePlayer = player[2];

        setPlayersOnPodium();
    }

    public void setPlayersOnPodium() {
        ((ImageView) window.getChildren().get(7)).setImage(firstPlacePlayer.getPlayerOnBoard().getImage());
        ((Label) window.getChildren().get(8)).setText(firstPlacePlayer.getFirstName());
        ((Label) window.getChildren().get(12)).setText(Integer.toString(firstPlacePlayer.getScore()));

        ((ImageView) window.getChildren().get(5)).setImage(secondPlacePlayer.getPlayerOnBoard().getImage());
        ((Label) window.getChildren().get(6)).setText(secondPlacePlayer.getFirstName());
        ((Label) window.getChildren().get(11)).setText(Integer.toString(secondPlacePlayer.getScore()));

        ((ImageView) window.getChildren().get(9)).setImage(thirdPlacePlayer.getPlayerOnBoard().getImage());
        ((Label) window.getChildren().get(10)).setText(thirdPlacePlayer.getFirstName());
        ((Label) window.getChildren().get(13)).setText(Integer.toString(thirdPlacePlayer.getScore()));
    }

    public Player[] getPlayers() {
        return players;
    }
    public void setPlayers(Player [] players){
        this.players = players;
    }
}
