package app.controller;

import app.MushroomPicking;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class GameController {

    // method combined with Restart button -> loads game again
    public void restart(ActionEvent event) {
        new MushroomPicking().start((Stage) ((Node) event.getSource()).getScene().getWindow());
    }

    // combined with Quit button -> exits program
    public void quit() {
        Platform.exit();
        System.exit(0);
    }
}
