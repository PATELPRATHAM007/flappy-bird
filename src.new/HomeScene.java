import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HomeScene extends Scene {
    private Stage parentStage;
    private Controller controller;

    public HomeScene() {
        super(new StackPane()); // Invoke the constructor of the superclass with a root Node parameter

        // starting scene
        StackPane startStackPane = (StackPane) getRoot();

        // add background
        ImageView background = new ImageView(new Image("img/city.png"));
        background.setPreserveRatio(true);
        background.setFitWidth(900);
        startStackPane.getChildren().add(background);

        // add buttons
        HBox buttonBox = new HBox();

        Button startButton = new Button("start");
        
        Button endButton = new Button("end");
        endButton.setOnAction(e -> controller.handleEvent(Controller.EXIT_GAME));

        buttonBox.getChildren().addAll(startButton, endButton);
        startStackPane.getChildren().add(buttonBox);
    }

    // set and get parent stage
    public void setParentStage(Stage stage) {
        parentStage = stage;
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }
}
