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

    // class constructor -> sets the group based on group from fxml file, creates Player class table
    public FinishGameWindowController(Group window) {
        this.window = window;
        players = new Player[4];
    }

    // sums up the game using setPositionsOnPodium() method, changes visibility of the Group
    public void show() {
        setPositionsOnPodium();
        window.setVisible(true);
    }

    // sums up the game
    public void setPositionsOnPodium() {

        // sorting players descending using bubble sort
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players.length - i - 1; j++) {
                int result1 = players[j].getScore();
                int result2 = players[j + 1].getScore();
                if (result1 < result2 || (result1 == result2 && players[j].getOrderAtEnd() < players[j + 1].getOrderAtEnd())) {
                    Player temp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temp;
                }
            }
        }
        firstPlacePlayer = players[0];
        secondPlacePlayer = players[1];
        thirdPlacePlayer = players[2];

        setPlayersOnPodium();
    }

    // sets the window look (correct information about who took which place n the game)
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

    public void setPlayers(Player [] players){
        this.players = players;
    }
}
