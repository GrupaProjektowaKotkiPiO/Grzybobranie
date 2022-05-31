package app;

import app.controller.*;
import app.service.MoveLogic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class MushroomPicking extends Application {

    // method to start the game window
    // loads view from fxml file
    @Override
    public void start(Stage stage) {
        try {
            Pane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("controller/fxml/Game.fxml")));
            Scene scene = new Scene(root);
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();

            Group finishGameWindow = (Group) root.getChildren().get(13);
            Group players = (Group) root.getChildren().get(11);
            Group mushrooms = (Group) root.getChildren().get(10);
            Group dice = (Group) root.getChildren().get(9);
            Group turnWindow = (Group) root.getChildren().get(8);
            Group table = (Group) root.getChildren().get(7);

            (new MoveLogic(new TileController(),
                    new PlayerController(players),
                    new DiceController(dice),
                    new PlayerTurnController(turnWindow),
                    new FinishGameWindowController(finishGameWindow),
                    new MushroomAndTableController(mushrooms, table))).
                    start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // main method -> starts the program
    // launch() method starts the start() method
    public static void main(String[] args) {
        launch();
    }
}