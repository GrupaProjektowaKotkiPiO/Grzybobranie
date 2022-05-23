package app.service;

import app.controller.*;
import app.dto.MoveType;
import app.dto.Player;
import app.dto.TileType;

import static app.dto.MoveType.*;

public class MoveLogic {
    public static int NUMBER_OF_PLAYERS = 4;
    private TileController tileController;
    private PlayerController playerController;
    private DiceController diceController;
    private PlayerTurnController displayWindowController;
    private FinishGameWindowController finishGameWindowController;
    private boolean winner;
    private int throwDiceCounter;
    private int newPosition;
    public MoveLogic(TileController inputTileController,
                     PlayerController inputPlayerController,
                     DiceController inputDiceController,
                     PlayerTurnController inputDisplayWindowController,
                     FinishGameWindowController inputFinishGameWindowController) {
        tileController = inputTileController;
        playerController = inputPlayerController;
        diceController = inputDiceController;
        displayWindowController = inputDisplayWindowController;
        finishGameWindowController = inputFinishGameWindowController;
        throwDiceCounter = 0;
        winner = true;
    }

    public void start() {
        visibleConfiguration();
        diceController.getMoveButton().setOnMousePressed(e -> {
            move();

            while(playerController.getPlayers()[getThrowDiceCounter()].getMoveType()==STAY) {
                playerController.getPlayers()[getThrowDiceCounter()].setMoveType(NORMAL);
                incrementThrowDiceCounter();
            }
            displayWindowController.changePlayerInWindow(playerController.getPlayers()[getThrowDiceCounter()]);
        });
    }

    private void move() {
        int i = getThrowDiceCounter();
        newPosition = playerController.getPlayers()[i].getPosition() + diceController.throwTheDice();

        if (playerController.getPlayers()[i].getMoveType() == REROLLBACKWARDS){
            newPosition = playerController.getPlayers()[i].getPosition() - diceController.throwTheDice();
            playerController.getPlayers()[i].setMoveType(NORMAL);
        }
        if(playerController.getPlayers()[i].getMoveType() == REROLL)
            playerController.getPlayers()[i].setMoveType(NORMAL);


        TileType type = tileController.getBoard()[newPosition].getType();
        switch (type) {
            case RABBIT:
                newPosition += 3;
                break;
            case MOUNTAIN:
                playerController.getPlayers()[i].setMoveType(STAY);
                break;
            case WATERFALL:
                playerController.getPlayers()[i].setMoveType(REROLL);
                break;
            case HILL:
                newPosition -= playerController.getPlayers()[i].getBrownMushrooms();
                break;
            case VALLEY:
                playerController.getPlayers()[i].setBrownMushrooms(playerController.getPlayers()[i].getBrownMushrooms() - 2);
                break;
            case THUNDER:
                //TODO: implementation of switching baskets
                break;
            case BOAR:
                playerController.getPlayers()[i].setMoveType(REROLLBACKWARDS);
                break;
        }

        playerController.getPlayers()[i].setPosition(newPosition);
        if (playerController.getPlayers()[i].getPosition() >= 36) {
            playerController.getPlayers()[i].setPosition(41);
            if (winner) {
                finishGameWindowController.setWinner(playerController.getPlayers()[i]);
                winner = false;
            }
        } else
            playerController.moveThePlayer(playerController.getPlayers()[i].getType(), tileController);

        if (playerController.getPlayers()[i].getMoveType() != REROLL &&
                playerController.getPlayers()[i].getMoveType() != REROLLBACKWARDS)
            incrementThrowDiceCounter();
        visibleConfiguration();

        if (playerController.getPlayers()[getThrowDiceCounter()].getPosition() == 41)
            if (isTheEndOfTheGame()) finishGameWindowController.show();
    }

    private boolean isTheEndOfTheGame() {
        int j = 0;
        while (j < NUMBER_OF_PLAYERS && isPlayerEnd()) j++;
        return j == NUMBER_OF_PLAYERS;
    }

    private boolean isPlayerEnd() {
        incrementThrowDiceCounter();
        return playerController.getPlayers()[getThrowDiceCounter()].getPosition() == 41;
    }

    private boolean isFree(int position) {
        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (position == playerController.getPlayers()[i].getPosition())
                return false;
        }
        return true;
    }

    private void visibleConfiguration() {
        if (throwDiceCounter < NUMBER_OF_PLAYERS) {
            playerController.getPlayers()[getThrowDiceCounter()].getPlayerOnBoard().setVisible(true);
            for (int i = 3; i > throwDiceCounter; i--)
                playerController.getPlayers()[i].getPlayerOnBoard().setVisible(false);
        }

        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (playerController.getPlayers()[i].getPosition() == 41) {
                playerController.getPlayers()[i].getPlayerOnBoard().setVisible(false);
            }

        }
    }


    private void incrementThrowDiceCounter() {
        throwDiceCounter++;
    }

    private int getThrowDiceCounter() {
        return throwDiceCounter % NUMBER_OF_PLAYERS;
    }
}