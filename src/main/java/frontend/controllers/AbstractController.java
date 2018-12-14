package frontend.controllers;

import frontend.util.SceneController;
import javafx.scene.control.Alert;
import model.game.AbstractGame;

public abstract class AbstractController {
    protected static AbstractGame game;
    protected SceneController sceneController;

    public void setGame(AbstractGame game) {
        this.game = game;
    }

    public void setSceneController(SceneController controller) {
        this.sceneController = controller;
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setTitle("Error");
        alert.show();
    }
}
