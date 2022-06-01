package app.service;

import app.controller.*;
import app.dto.TileType;

import static app.dto.MoveType.*;
//todo throwDiceCounter -> czyja jest tura
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
    private final SpecialTileInfoController SpecialTileInfoController;
    private boolean firstBehindLine;
    private boolean secondBehindLine;
    private boolean thirdBehindLine;
    private int throwDiceCounter;

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
        SpecialTileInfoController = inputSpecialTileInfoController;
        throwDiceCounter = 0;
        firstBehindLine = true;
        secondBehindLine = true;
        thirdBehindLine = true;
    }

    //
    public void start() {
        visibleConfiguration();
        diceController.getMoveButton().setOnMousePressed(e -> {
            move();

            while (playerController.getPlayers()[getThrowDiceCounter()].getMoveType() == STAY) {
                playerController.getPlayers()[getThrowDiceCounter()].setMoveType(NORMAL);
                incrementThrowDiceCounter();
            }
            displayWindowController.changePlayerInWindow(playerController.getPlayers()[getThrowDiceCounter()]);
            finishGameWindowController.setPlayers(playerController.getPlayers());
        });
    }

    //
    private void move() {
        //todo i = indexgracza
        int i = getThrowDiceCounter();
        int newPosition = playerController.getPlayers()[i].getPosition() + diceController.throwTheDice();

        if (playerController.getPlayers()[i].getMoveType() == REROLLBACKWARDS) {
            newPosition = playerController.getPlayers()[i].getPosition() - diceController.throwTheDice();
            playerController.getPlayers()[i].setMoveType(NORMAL);
        }
        if (playerController.getPlayers()[i].getMoveType() == REROLL) {
            playerController.getPlayers()[i].setMoveType(NORMAL);
        }

        if (newPosition < FINISH_LINE) {
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
                    if (playerController.getPlayers()[i].getBoletusCounter() >= 2)
                        playerController.getPlayers()[i].setBoletusCounter(playerController.getPlayers()[i].getBoletusCounter() - 2);
                    else if (playerController.getPlayers()[i].getBoletusCounter() == 1)
                        playerController.getPlayers()[i].setBoletusCounter(playerController.getPlayers()[i].getBoletusCounter() - 1);
                    else
                        playerController.getPlayers()[i].setBoletusCounter(0);
                    break;
                case THUNDER:
                    if (playerController.getPlayers()[i].getBoletusCounter() > 0)
                        playerController.getPlayers()[i].setBoletusCounter(playerController.getPlayers()[i].getBoletusCounter() - 1);
                    if (playerController.getPlayers()[i].getToadstoalCounter() > 0)
                        playerController.getPlayers()[i].setToadstoalCounter(playerController.getPlayers()[i].getToadstoalCounter() - 1);
                    break;
                case BOAR:
                    playerController.getPlayers()[i].setMoveType(REROLLBACKWARDS);
                    break;
            }
        }

        //todo getpos -> newpos
        playerController.getPlayers()[i].setPosition(newPosition);
        if (playerController.getPlayers()[i].getPosition() >= FINISH_LINE) {
            playerController.getPlayers()[i].setPosition(OUT_OF_BOARD);
            if (firstBehindLine) {
                playerController.getPlayers()[i].setOrderAtEnd(3);
                firstBehindLine = false;
            } else if (secondBehindLine){
                playerController.getPlayers()[i].setOrderAtEnd(2);
                secondBehindLine = false;
            } else if (thirdBehindLine){
                playerController.getPlayers()[i].setOrderAtEnd(1);
                thirdBehindLine = false;
            }
        } else {
            playerController.moveThePlayer(playerController.getPlayers()[i].getType(), tileController);
        }

        if (playerController.getPlayers()[i].getMoveType() != REROLL &&
                playerController.getPlayers()[i].getMoveType() != REROLLBACKWARDS) {
            mushroomController.changeMushroomVisibility(playerController.getPlayers()[i]);
            incrementThrowDiceCounter();
        }
        visibleConfiguration();

        //todo do poprawy z met ponizej - word
        if (playerController.getPlayers()[getThrowDiceCounter()].getPosition() == OUT_OF_BOARD && isTheEndOfTheGame()) {
                finishGameWindowController.show();
        }
    }

    private boolean isTheEndOfTheGame() {
        int j = 0;
        while (j < NUMBER_OF_PLAYERS && isPlayerEnd()) {
            j++;
        }
        return j == NUMBER_OF_PLAYERS;
    }

    private boolean isPlayerEnd() {
        incrementThrowDiceCounter();
        return playerController.getPlayers()[getThrowDiceCounter()].getPosition() == OUT_OF_BOARD;
    }

    // na starcie i endzie zeby tylko 1 gracz byl widoczny
    private void visibleConfiguration() {
        if (throwDiceCounter < NUMBER_OF_PLAYERS) {
            playerController.getPlayers()[getThrowDiceCounter()].getPlayerOnBoard().setVisible(true);
            for (int i = 3; i > throwDiceCounter; i--) {
                playerController.getPlayers()[i].getPlayerOnBoard().setVisible(false);
            }
        }

        for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
            if (playerController.getPlayers()[i].getPosition() == OUT_OF_BOARD) {
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