package app.controller;

import app.service.MoveLogic;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    private Stage stage;

    public void restart(ActionEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("fxml/Game.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();

        Group finishGameWindow= (Group) root.getChildren().get(13);
        Group players= (Group) root.getChildren().get(11);
        Group mushrooms= (Group) root.getChildren().get(10);
        Group dice= (Group) root.getChildren().get(9);
        Group turnWindow= (Group) root.getChildren().get(8);
        Group table= (Group) root.getChildren().get(7);

        (new MoveLogic(new TileController(),
                new PlayerController(players),
                new DiceController(dice),
                new PlayerTurnController(turnWindow),
                new FinishGameWindowController(finishGameWindow),
                new MushroomAndTableController(mushrooms, table))).
                start();
    }

    public void quit() {
        Platform.exit();
        System.exit(0);
    }
}
