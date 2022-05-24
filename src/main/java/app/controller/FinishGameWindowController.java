package app.controller;

import app.dto.Player;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class FinishGameWindowController {
    private Player players[];
    private Player firstPlacePlayer;
    private Player secondPlacePlayer;
    private Player thirdPlacePlayer;
    private int firstPlaceScore;
    private int secondPlaceScore;
    private int thirdPlaceScore;
    private Group window;

    public FinishGameWindowController(Group window) {
        firstPlacePlayer=null;
        secondPlacePlayer=null;
        thirdPlacePlayer=null;
        this.window=window;
        players=new Player[4];
    }

    public void show() {
        firstPlaceScore=-10;
        secondPlaceScore=-11;
        thirdPlaceScore=-12;

        for (int i=0; i<4; i++)
            positionOnPodium(players[i]);

        window.setVisible(true);
    }

    public void positionOnPodium(Player player) {
        int result = player.getBoletusCounter() - player.getToadstoalCounter() + player.getBonus();
        if (result > firstPlaceScore) {
            firstPlaceScore=result;

            if(firstPlacePlayer !=null)
                positionOnPodium(firstPlacePlayer);
            firstPlacePlayer=player;

            ((ImageView) window.getChildren().get(7)).setImage(player.getPlayerOnBoard().getImage());
            ((Label) window.getChildren().get(8)).setText(player.getFirstName());
            ((Label) window.getChildren().get(12)).setText(Integer.toString(result));
        } else {
            if (result > secondPlaceScore) {
                secondPlaceScore = result;

                if(secondPlacePlayer !=null)
                    positionOnPodium(secondPlacePlayer);
                secondPlacePlayer =player;

                ((ImageView) window.getChildren().get(5)).setImage(player.getPlayerOnBoard().getImage());
                ((Label) window.getChildren().get(6)).setText(player.getFirstName());
                ((Label) window.getChildren().get(11)).setText(Integer.toString(result));
            } else {
                if (result > thirdPlaceScore) {
                    thirdPlaceScore = result;

                    if(thirdPlacePlayer !=null)
                        positionOnPodium(thirdPlacePlayer);
                    thirdPlacePlayer =player;

                    ((ImageView) window.getChildren().get(9)).setImage(player.getPlayerOnBoard().getImage());
                    ((Label) window.getChildren().get(10)).setText(player.getFirstName());
                    ((Label) window.getChildren().get(13)).setText(Integer.toString(result));
                }
            }
        }
    }

    public Player[] getPlayers() { return players; }
}
