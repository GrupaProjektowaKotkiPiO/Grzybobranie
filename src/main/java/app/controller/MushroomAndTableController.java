package app.controller;

import app.dto.Mushroom;
import app.dto.MushroomType;
import app.dto.Player;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class MushroomAndTableController {
    private Mushroom[] mushrooms;
    private Label[][] scoreTable;

    public MushroomAndTableController(Group inputMushrooms, Group inputTable) {
        mushrooms=new Mushroom[28];
        scoreTable=new Label[3][4];

        for(int i=0, j=1; i<28; i++) {
            if(i==0 || i==6 || i==8 || i==11 || i==13 || i==16 || i==18 || i==23 || i==26)
                mushrooms[i]=new Mushroom(MushroomType.TOADSTOAL,(ImageView)inputMushrooms.getChildren().get(i),j);
            else
                mushrooms[i]=new Mushroom(MushroomType.BOLETUS,(ImageView)inputMushrooms.getChildren().get(i),j);

            j+=1;
            if(j==4 || j==11 || j==17 || j==21 || j==23 || j==26 || j==31)
                j+=1;
        }

        for(int i=5,j=0; i<17 && j<4; j++) {
            for(int k=0; k<3; k++, i++)
                scoreTable[k][j]=(Label)inputTable.getChildren().get(i);
        }
    }

    public void changeMushroomVisibility(Player player) {
        for(int i=0; i<28; i++) {
            if (mushrooms[i].getPositionOnBoard()==player.getPosition() && mushrooms[i].getMushroomOnBoard().isVisible()) {
                mushrooms[i].getMushroomOnBoard().setVisible(false);

                if(mushrooms[i].getType()==MushroomType.BOLETUS)
                    player.setBoletusCounter(player.getBoletusCounter()+1);
                else
                    player.setToadstoalCounter(player.getToadstoalCounter()+1);

                int j;
                switch (player.getType()) {
                    case PLAYER1:
                        j=0;
                        break;
                    case PLAYER2:
                        j=1;
                        break;
                    case PLAYER3:
                        j=2;
                        break;
                    default:
                        j=3;
                }

                scoreTable[0][j].setText(Integer.toString(player.getBoletusCounter()));
                scoreTable[1][j].setText(Integer.toString(player.getToadstoalCounter()));
                scoreTable[2][j].setText(Integer.toString(player.getBoletusCounter()-player.getToadstoalCounter()));
            }
        }
    }
}
