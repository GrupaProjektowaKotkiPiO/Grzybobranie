package app.controller;

import app.dto.Tile;
import app.dto.TileType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class TileController {
    private Tile[] board;

    public TileController() {
        board = new Tile[37];
        board[0] = new Tile(TileType.START, 81, 71);
        board[1] = new Tile(TileType.RED_MUSHROM, 182, 129);
        board[2] = new Tile(TileType.BROWN_MUSHROM, 86, 213);
        board[3] = new Tile(TileType.NORMAL, 186, 259);
        board[4] = new Tile(TileType.RABBIT, 288, 257);
        board[5] = new Tile(TileType.BROWN_MUSHROM, 404, 244);
        board[6] = new Tile(TileType.NORMAL, 360, 158);
        board[7] = new Tile(TileType.BROWN_MUSHROM, 288, 85);
        board[8] = new Tile(TileType.RED_MUSHROM, 418, 72);
        board[9] = new Tile(TileType.NORMAL, 512, 129);
        board[10] = new Tile(TileType.RED_MUSHROM, 555, 231);
        board[11] = new Tile(TileType.MOUNTAIN, 613, 157);
        board[12] = new Tile(TileType.BROWN_MUSHROM, 684, 86);
        board[13] = new Tile(TileType.NORMAL, 744, 172);
        board[14] = new Tile(TileType.RED_MUSHROM, 744, 275);
        board[15] = new Tile(TileType.NORMAL, 658, 343);
        board[16] = new Tile(TileType.NORMAL, 544, 342);
        board[17] = new Tile(TileType.WATERFALL, 437, 343);
        board[18] = new Tile(TileType.NORMAL, 331, 343);
        board[19] = new Tile(TileType.NORMAL, 229, 360);
        board[20] = new Tile(TileType.NORMAL, 124, 415);
        board[21] = new Tile(TileType.HILL, 97, 525);
        board[22] = new Tile(TileType.NORMAL, 96, 637);
        board[23] = new Tile(TileType.VALLEY, 173, 725);
        board[24] = new Tile(TileType.NORMAL, 288, 725);
        board[25] = new Tile(TileType.NORMAL, 404, 725);
        board[26] = new Tile(TileType.THUNDER, 448, 631);
        board[27] = new Tile(TileType.NORMAL, 331, 609);
        board[28] = new Tile(TileType.NORMAL, 224, 580);
        board[29] = new Tile(TileType.NORMAL, 272, 481);
        board[30] = new Tile(TileType.NORMAL, 393, 471);
        board[31] = new Tile(TileType.BOAR, 516, 471);
        board[32] = new Tile(TileType.NORMAL, 640, 480);
        board[33] = new Tile(TileType.NORMAL, 719, 557);
        board[34] = new Tile(TileType.NORMAL, 615, 608);
        board[35] = new Tile(TileType.NORMAL, 615, 711);
        board[36] = new Tile(TileType.FINISH, 727, 711);
        generateMushrooms(board);
    }


    public Tile[] getBoard() {
        return board;
    }
    public void generateMushrooms(Tile []tiles){
        for (int i =0;i<tiles.length;i++){
            if (tiles[i].getType() == TileType.BROWN_MUSHROM){
                tiles[i].getImageView().setImage(new Image(getClass().getResourceAsStream("css/images/borowik.png")));
                tiles[i].getImageView().relocate(tiles[i].getBasketX(),tiles[i].getBasketY());


            }
        }

    }
}
