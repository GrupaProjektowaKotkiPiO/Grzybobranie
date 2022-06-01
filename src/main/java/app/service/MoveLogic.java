package app.service;

import app.controller.*;
import app.dto.TileType;

import static app.dto.MoveType.*;

public class MoveLogic {
    public final static int NUMBER_OF_PLAYERS = 4;
    public final static int OUT_OF_BOARD = 41;
    public static int FINISH_LINE = 36;
    private final TileController tileController;
    private final PlayerController playerController;
    private final DiceController diceController;
    private final PlayerTurnController displayWindowController;
    private final FinishGameWindowController finishGameWindowController;
    private final MushroomAndTableController mushroomController;
    private final SpecialTileInfoController specialTileInfoController;
    private boolean firstBehindLine;
    private boolean secondBehindLine;
    private boolean thirdBehindLine;
    private int whoseTurn;

    // class constructor
    public MoveLogic(TileController inputTileController,
                     PlayerController inputPlayerController,
                     DiceController inputDiceController,
                     PlayerTurnController inputDisplayWindowController,
                     FinishGameWindowController inputFinishGameWindowController,
                     MushroomAndTableController inputMushroomController, SpecialTileInfoController inputSpecialTileInfoController) {
        tileController = inputTileController;
        playerController = inputPlayerController;
        diceController = inputDiceController;
        displayWindowController = inputDisplayWindowController;
        finishGameWindowController = inputFinishGameWindowController;
        mushroomController = inputMushroomController;
        specialTileInfoController = inputSpecialTileInfoController;
        whoseTurn = 0;
        firstBehindLine = true;
        secondBehindLine = true;
        thirdBehindLine = true;
    }

    // main class method, used to start & control the game
    public void start() {
        visibleConfiguration();
        diceController.getMoveButton().setOnMousePressed(e -> {
            move();

            while (playerController.getPlayers()[getWhoseTurn()].getMoveType() == STAY) {
                playerController.getPlayers()[getWhoseTurn()].setMoveType(NORMAL);
                nextPlayerTurn();
            }
            displayWindowController.changePlayerInWindow(playerController.getPlayers()[getWhoseTurn()]);
            finishGameWindowController.setPlayers(playerController.getPlayers());
        });
    }

    // implementation of move logic
    private void move() {
        int playerIndex = getWhoseTurn();
        int newPosition = playerController.getPlayers()[playerIndex].getPosition() + diceController.throwTheDice();

        if (playerController.getPlayers()[playerIndex].getMoveType() == REROLLBACKWARDS) {
            newPosition = playerController.getPlayers()[playerIndex].getPosition() - diceController.throwTheDice();
            playerController.getPlayers()[playerIndex].setMoveType(NORMAL);
        }
        if (playerController.getPlayers()[playerIndex].getMoveType() == REROLL) {
            playerController.getPlayers()[playerIndex].setMoveType(NORMAL);
        }

        if (newPosition < FINISH_LINE) {
            TileType type = tileController.getBoard()[newPosition].getType();
            newPosition = specialTileEvent(playerIndex, newPosition, type);
            if(type.ordinal() > 1) {
                specialTileInfoController.show(playerController.getPlayers()[playerIndex].getFirstName(), type);
            }
        }

        playerController.getPlayers()[playerIndex].setPosition(newPosition);
        if (newPosition >= FINISH_LINE) {
            additionalBonusPoints(playerIndex);
        } else {
            playerController.moveThePlayer(playerController.getPlayers()[playerIndex].getType(), tileController);
        }

        if (playerController.getPlayers()[playerIndex].getMoveType() != REROLL &&
                playerController.getPlayers()[playerIndex].getMoveType() != REROLLBACKWARDS) {
            mushroomController.changeMushroomVisibility(playerController.getPlayers()[playerIndex]);
            nextPlayerTurn();
        }
        visibleConfiguration();

        if (isTheEndOfTheGame()) {
                finishGameWindowController.show();
        }
    }

    // method used to add points to 3 top players for being the first ones to cross the finish line
    private void additionalBonusPoints(int playerIndex) {
        playerController.getPlayers()[playerIndex].setPosition(OUT_OF_BOARD);
        if (firstBehindLine) {
            playerController.getPlayers()[playerIndex].setOrderAtEnd(3);
            firstBehindLine = false;
        } else if (secondBehindLine){
            playerController.getPlayers()[playerIndex].setOrderAtEnd(2);
            secondBehindLine = false;
        } else if (thirdBehindLine){
            playerController.getPlayers()[playerIndex].setOrderAtEnd(1);
            thirdBehindLine = false;
        }
    }

    // logic of special tiles (what happens when you step on one of them)
    private int specialTileEvent(int playerIndex, int newPosition, TileType type) {
        switch (type) {
            case RABBIT:
                newPosition += 3;
                break;
            case MOUNTAIN:
                playerController.getPlayers()[playerIndex].setMoveType(STAY);
                break;
            case WATERFALL:
                playerController.getPlayers()[playerIndex].setMoveType(REROLL);
                break;
            case HILL:
                newPosition -= playerController.getPlayers()[playerIndex].getBoletusCounter();
                break;
            case VALLEY:
                if (playerController.getPlayers()[playerIndex].getBoletusCounter() >= 2)
                    playerController.getPlayers()[playerIndex].setBoletusCounter(playerController.getPlayers()[playerIndex].getBoletusCounter() - 2);
                else if (playerController.getPlayers()[playerIndex].getBoletusCounter() == 1)
                    playerController.getPlayers()[playerIndex].setBoletusCounter(playerController.getPlayers()[playerIndex].getBoletusCounter() - 1);
                else
                    playerController.getPlayers()[playerIndex].setBoletusCounter(0);
                break;
            case THUNDER:
                if (playerController.getPlayers()[playerIndex].getBoletusCounter() > 0)
                    playerController.getPlayers()[playerIndex].setBoletusCounter(playerController.getPlayers()[playerIndex].getBoletusCounter() - 1);
                if (playerController.getPlayers()[playerIndex].getToadstoalCounter() > 0)
                    playerController.getPlayers()[playerIndex].setToadstoalCounter(playerController.getPlayers()[playerIndex].getToadstoalCounter() - 1);
                break;
            case BOAR:
                playerController.getPlayers()[playerIndex].setMoveType(REROLLBACKWARDS);
                break;
        }
        return newPosition;
    }

    // checks if all players has crossed the finish line
    private boolean isTheEndOfTheGame() {
        int playerNumber = 0;
        while (playerNumber < NUMBER_OF_PLAYERS && havePlayerFinishedGame(playerNumber)) {
            playerNumber++;
        }
        return playerNumber == NUMBER_OF_PLAYERS;
    }

    // returns true if player has crossed the finish line
    private boolean havePlayerFinishedGame(int index) {
        return playerController.getPlayers()[index].getPosition() == OUT_OF_BOARD;
    }

    // sets players visibility -> you can see only 1 player at start & finish tile
    private void visibleConfiguration() {
        if(!everyoneMoved()) {
            playerController.getPlayers()[getWhoseTurn()].getPlayerOnBoard().setVisible(true);
            for (int i = 3; i > whoseTurn; i--) {
                playerController.getPlayers()[i].getPlayerOnBoard().setVisible(false);
            }
        }

        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (playerController.getPlayers()[i].getPosition() == OUT_OF_BOARD) {
                playerController.getPlayers()[i].getPlayerOnBoard().setVisible(false);
            }
        }
    }

    // checks if every player has moved from start tile
    private boolean everyoneMoved() {
        for(int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if(playerController.getPlayers()[i].getPosition() == 0) {
                return false;
            }
        }

        return true;
    }

    // changes the player's turn which hasn't ended yet
    private void nextPlayerTurn() {
        do {
            whoseTurn++;
        } while (playerController.getPlayers()[getWhoseTurn()].getPosition() == OUT_OF_BOARD && !isTheEndOfTheGame());
    }

    // gets the player number whose turn is now
    private int getWhoseTurn() {
        return whoseTurn % NUMBER_OF_PLAYERS;
    }
}