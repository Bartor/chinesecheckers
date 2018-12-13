package frontend.controllers;

import frontend.SceneController;
import model.game.AbstractGame;

abstract class AbstractController {
    AbstractGame game;
    SceneController sceneController;

    void setGame(AbstractGame game) {
        this.game = game;
    }

    void setSceneController(SceneController controller) {
        this.sceneController = controller;
    }
}
