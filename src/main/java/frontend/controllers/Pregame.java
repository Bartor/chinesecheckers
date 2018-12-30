package frontend.controllers;

import frontend.networking.MessageInterpreter;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class Pregame extends AbstractController {
    @FXML
    VBox playerList;

    @FXML
    public void initialize() {
        MessageInterpreter.setController(this);
    }
}
