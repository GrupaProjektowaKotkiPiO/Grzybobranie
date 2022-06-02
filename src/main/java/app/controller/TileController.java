package app.controller;

import app.dto.Tile;
import app.dto.TileType;
import app.service.LoadFromFile;

import java.io.FileNotFoundException;

public class TileController {
    public static final int BOARD_SIZE = 37;
    private final Tile[] board = new Tile[BOARD_SIZE];

    // class constructor -> sets tiles
    public TileController() throws FileNotFoundException {
        int[][] basketCords = (new LoadFromFile()).load();

        final int[] tilesPosTable = {0, 4, 11, 17, 21, 23, 26, 31, 36};

        for(int indexBoard = 0, specialTiles = 1; indexBoard < BOARD_SIZE; indexBoard++) {
            if(checkIfSpecialTile(tilesPosTable, indexBoard)) {
                board[indexBoard] = new Tile(TileType.values()[specialTiles++], basketCords[indexBoard][0], basketCords[indexBoard][1]);
            }
            else {
                board[indexBoard] = new Tile(TileType.NORMAL, basketCords[indexBoard][0], basketCords[indexBoard][1]);
            }
        }
    }

    private boolean checkIfSpecialTile(int[] tilesPosTable, int index) {
        for(int specialTile : tilesPosTable) {
            if(specialTile == index){
                return true;
            }
        }
        return false;
    }

    public Tile[] getBoard() {
        return board;
    }
}
