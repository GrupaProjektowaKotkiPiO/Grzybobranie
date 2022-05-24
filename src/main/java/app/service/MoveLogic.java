package app.service;

import app.controller.*;
import app.dto.TileType;

import static app.dto.MoveType.*;

public class MoveLogic {
    public static int NUMBER_OF_PLAYERS = 4;
    private TileController tileController;
    private PlayerController playerController;
    private DiceController diceController;
    private PlayerTurnController displayWindowController;
    private FinishGameWindowController finishGameWindowController;
    private MushroomAndTableController mushroomController;
    private boolean firstBehindLine;
    private boolean secondBehindLine;
    private boolean thirdBehindLine;
    private int throwDiceCounter;
    private int newPosition;
    public MoveLogic(TileController inputTileController,
                     PlayerController inputPlayerController,
                     DiceController inputDiceController,
                     PlayerTurnController inputDisplayWindowController,
                     FinishGameWindowController inputFinishGameWindowController,
                     MushroomAndTableController inputMushroomController) {
        tileController = inputTileController;
        playerController = inputPlayerController;
        diceController = inputDiceController;
        displayWindowController = inputDisplayWindowController;
        finishGameWindowController = inputFinishGameWindowController;
        mushroomController=inputMushroomController;
        throwDiceCounter = 0;
        firstBehindLine = true;
        secondBehindLine = true;
        thirdBehindLine = true;
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

        if(newPosition<37) {
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
                    newPosition -= playerController.getPlayers()[i].getBoletusCounter();
                    break;
                case VALLEY:
                    playerController.getPlayers()[i].setBoletusCounter(playerController.getPlayers()[i].getBoletusCounter() - 2);
                    break;
                case THUNDER:
                    playerController.getPlayers()[i].setBoletusCounter(playerController.getPlayers()[i].getBoletusCounter() - 1);
                    playerController.getPlayers()[i].setToadstoalCounter(playerController.getPlayers()[i].getToadstoalCounter() - 2);
                    break;
                case BOAR:
                    playerController.getPlayers()[i].setMoveType(REROLLBACKWARDS);
                    break;
            }
        }

        playerController.getPlayers()[i].setPosition(newPosition);
        if (playerController.getPlayers()[i].getPosition() >= 36) {
            playerController.getPlayers()[i].setPosition(41);
            if (firstBehindLine) {
                playerController.getPlayers()[i].setBonus(3);
                finishGameWindowController.getPlayers()[0]=playerController.getPlayers()[i];
                firstBehindLine = false;
            }
            else {
                if(secondBehindLine) {
                    playerController.getPlayers()[i].setBonus(2);
                    finishGameWindowController.getPlayers()[1]=playerController.getPlayers()[i];
                    secondBehindLine = false;
                }
                else {
                    if(thirdBehindLine) {
                        playerController.getPlayers()[i].setBonus(1);
                        finishGameWindowController.getPlayers()[2]=playerController.getPlayers()[i];
                        thirdBehindLine = false;
                    }
                }
            }
        } else {
            playerController.moveThePlayer(playerController.getPlayers()[i].getType(), tileController);
            finishGameWindowController.getPlayers()[3]=playerController.getPlayers()[i];
        }

        if (playerController.getPlayers()[i].getMoveType() != REROLL &&
                playerController.getPlayers()[i].getMoveType() != REROLLBACKWARDS) {
            mushroomController.changeMushroomVisibility(playerController.getPlayers()[i]);
            incrementThrowDiceCounter();
        }
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