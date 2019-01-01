package frontend.util;

import com.sun.istack.internal.NotNull;
import frontend.controllers.AbstractController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;

public class SceneController {
    private Stage stage;
    private HashMap<String, Pair<Scene, AbstractController>> scenes = new HashMap<>();

    public SceneController(Stage stage) {
        this.stage = stage;
    }

    public void addScene(String name, @NotNull Scene scene, @NotNull AbstractController controller) {
        this.scenes.put(name, new Pair<>(scene, controller));
    }

    public void switchScene(String name) throws Exception{
        if (this.scenes.containsKey(name)) {
            this.stage.setScene(this.scenes.get(name).getKey());
            this.scenes.get(name).getValue().onSwitch();
        } else {
            throw new Exception("Scene does not exist");
        }
    }
    public Scene getScene(String name) throws Exception {
        if (this.scenes.containsKey(name)) return this.scenes.get(name).getKey();
        else throw new Exception("There is no such scene");
    }

    public Stage getStage() {
        return stage;
    }
}
