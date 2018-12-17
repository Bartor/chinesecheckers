package frontend;

import frontend.controllers.AbstractController;
import frontend.util.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChineseCheckersApplication extends Application {
    private void loadSceneFromPath(SceneController sceneController, String name) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/" + name + ".fxml"));
        try {
            Parent root = loader.load();
            sceneController.addScene(name, new Scene(root, 800, 600));
            AbstractController controller = loader.getController();
            controller.setSceneController(sceneController);
        } catch (LoadException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void start(Stage stage) {
        SceneController sceneController = new SceneController(stage);
        try {
            loadSceneFromPath(sceneController, "start");
            loadSceneFromPath(sceneController, "game");
            stage.setTitle("Chinese Checkers");
            stage.setScene(sceneController.getScene("start"));
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
