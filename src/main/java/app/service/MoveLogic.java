package app.service;

import app.controller.*;

public class MoveLogic {
    private TileController tileController;
    private PlayerController playerController;
    private DiceController diceController;
    private PlayerTurnController displayWindowController;
    private FinishGameWindowController finishGameWindowController;
    private boolean winner;
    private int throwDiceCounter;

    public MoveLogic(TileController inputTileController,
                     PlayerController inputPlayerController,
                     DiceController inputDiceController,
                     PlayerTurnController inputDisplayWindowController,
                     FinishGameWindowController inputFinishGameWindowController) {
        tileController = inputTileController;
        playerController = inputPlayerController;
        diceController = inputDiceController;
        displayWindowController = inputDisplayWindowController;
        finishGameWindowController=inputFinishGameWindowController;
        throwDiceCounter=0;
        winner=true;
    }

    public void start() {
        visibleConfiguration();
        diceController.getMoveButton().setOnMousePressed(e -> {
            int i=getThrowDiceCounter();

            move();

            displayWindowController.changePlayerInWindow(playerController.getPlayers()[getThrowDiceCounter()]);
        });
    }

    private void move() {
        int i=getThrowDiceCounter();

        int newPosition=playerController.getPlayers()[i].getPosition() + diceController.throwTheDice();
        while (!isFree(newPosition))
            newPosition=playerController.getPlayers()[i].getPosition() + diceController.throwTheDice();
        playerController.getPlayers()[i].setPosition(newPosition);

        if (playerController.getPlayers()[i].getPosition() >= 36) {
            playerController.getPlayers()[i].setPosition(41);
            if(winner) {
                finishGameWindowController.setWinner(playerController.getPlayers()[i]);
                winner=false;
            }
        }
        else
            playerController.moveThePlayer(playerController.getPlayers()[i].getType(), tileController);

        incrementThrowDiceCounter();
        visibleConfiguration();

        if(playerController.getPlayers()[getThrowDiceCounter()].getPosition()==41) {
            if (isTheEndOfTheGame()) finishGameWindowController.show();
        }
    }

    private boolean isTheEndOfTheGame() {
        int j=0;
        while (j<4 && isPlayerEnd()) j++;
        return j==4;
    }

    private boolean isPlayerEnd() {
        incrementThrowDiceCounter();
        return playerController.getPlayers()[getThrowDiceCounter()].getPosition() == 41;
    }

    private boolean isFree(int position) {
        for(int i=0; i<4; i++) {
            if(position==playerController.getPlayers()[i].getPosition())
                return false;
        }
        return true;
    }

    private void visibleConfiguration() {
        if(throwDiceCounter<4) {
            playerController.getPlayers()[getThrowDiceCounter()].getPlayerOnBoard().setVisible(true);
            for (int i=3; i>throwDiceCounter; i--)
                playerController.getPlayers()[i].getPlayerOnBoard().setVisible(false);
        }

        for (int i=0; i<4; i++) {
            if(playerController.getPlayers()[i].getPosition()==41)
                playerController.getPlayers()[i].getPlayerOnBoard().setVisible(false);
        }
    }

    private void incrementThrowDiceCounter() { throwDiceCounter++; }

    private int getThrowDiceCounter() { return throwDiceCounter %4; }
}