package app;

import app.controller.*;
import app.service.MoveLogic;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MushroomPicking extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Pane root = FXMLLoader.load(getClass().getResource("controller/fxml/Game.fxml"));
            Scene scene = new Scene(root);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();

            Group finishGameWindow=(Group)root.getChildren().get(9);
            ObservableList<Node> players=((Group)root.getChildren().get(7)).getChildren();
            ObservableList<Node> dice=((Group)root.getChildren().get(6)).getChildren();
            ObservableList<Node> turnWindow=((Group)root.getChildren().get(5)).getChildren();

            (new MoveLogic(new TileController(), new PlayerController(players), new DiceController(dice), new PlayerTurnController(turnWindow), new FinishGameWindowController(finishGameWindow))).start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}