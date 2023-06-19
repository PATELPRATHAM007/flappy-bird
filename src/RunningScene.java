import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class RunningScene extends Scene {
    private Stage parentStage;
    private Controller controller;

    public RunningScene() {
        super(new StackPane()); // Invoke the constructor of the superclass with a root Node parameter

        // running scene
        StackPane runStackPane = (StackPane) getRoot();

        // add background
        ImageView background = new ImageView(new Image("img/city.png"));
        background.setPreserveRatio(true);
        background.setFitWidth(900);
        background.setOpacity(0.8);
        runStackPane.getChildren().add(background);

        // animination
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            }
        };

        animationTimer.start();

        // temp
        System.out.println(controller);
        Button startButton = new Button("start");
        startButton.setOnAction(e -> controller.handleEvent(Controller.END_SCREEN));
        runStackPane.getChildren().add(startButton);
    }

    // setters and getters
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