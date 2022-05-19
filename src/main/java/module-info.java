module app.controller {
    requires javafx.controls;
    requires javafx.fxml;

    opens app.controller to javafx.fxml;
    exports app;
    exports app.controller;
}