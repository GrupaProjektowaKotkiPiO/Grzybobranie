package app.controller;

import app.service.MoveLogic;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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

        Group finishGameWindow=(Group)root.getChildren().get(9);
        ObservableList<Node> players=((Group)root.getChildren().get(7)).getChildren();
        ObservableList<Node> dice=((Group)root.getChildren().get(6)).getChildren();
        ObservableList<Node> turnWindow=((Group)root.getChildren().get(5)).getChildren();

        (new MoveLogic(new TileController(), new PlayerController(players), new DiceController(dice), new PlayerTurnController(turnWindow), new FinishGameWindowController(finishGameWindow))).start();
    }

    public void quit() {
        Platform.exit();
        System.exit(0);
    }
}
