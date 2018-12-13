package frontend;

import com.sun.istack.internal.NotNull;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.HashSet;

public class SceneController {
    private Stage stage;
    private HashMap<String, Scene> scenes = new HashMap<String, Scene>();

    public SceneController(Stage stage) {
        this.stage = stage;
    }

    public void addScene(String name, @NotNull Scene scene) {
        this.scenes.put(name, scene);
    }

    public void switchScene(String name) throws Exception{
        if (this.scenes.containsKey(name)) {
            this.stage.setScene(this.scenes.get(name));
        } else {
            throw new Exception("Scene does not exist");
        }
    }
    public Scene getScene(String name) throws Exception {
        if (this.scenes.containsKey(name)) return this.scenes.get(name);
        else throw new Exception("There is no such scene");
    }

    public Stage getStage() {
        return stage;
    }
}
