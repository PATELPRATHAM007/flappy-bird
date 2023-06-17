import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class RunningScene extends Scene {
    private Stage parentStage;

    public RunningScene() {
        super(new StackPane());
    }

    // set and get parent stage
    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage stage) {
        parentStage = stage;
    }

}