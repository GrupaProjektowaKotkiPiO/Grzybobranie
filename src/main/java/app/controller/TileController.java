package app.controller;

import app.dto.Tile;
import app.dto.TileType;
import app.service.LoadFromFile;

import java.io.FileNotFoundException;

public class TileController {
    private final Tile[] board;

    // class constructor -> sets tiles
    public TileController() throws FileNotFoundException {
        board = new Tile[37];

        int[][] basketCords = (new LoadFromFile()).load();

        for(int indexBoard = 0, specialTiles = 1; indexBoard < 37; indexBoard++) {
            if(indexBoard == 4 || indexBoard == 11 || indexBoard == 17 || indexBoard == 21 || indexBoard == 23 || indexBoard == 26 || indexBoard == 31) {
                board[indexBoard] = new Tile(TileType.values()[specialTiles++], basketCords[indexBoard][0], basketCords[indexBoard][1]);
            }
            else {
                board[indexBoard] = new Tile(TileType.NORMAL, basketCords[indexBoard][0], basketCords[indexBoard][1]);
            }
        }
    }

    public Tile[] getBoard() {
        return board;
    }
}
