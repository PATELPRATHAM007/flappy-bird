import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
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
        background.setOpacity(0.5);
        startStackPane.getChildren().add(background);

        // add buttons
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);

        Button startButton = new Button("play");
        startButton.setOnAction(e -> controller.handleEvent(Controller.START_GAME));
        startButton.setDefaultButton(true);
        startButton.setFont(Font.font("Consolas", 20));

        Button endButton = new Button("exit");
        endButton.setOnAction(e -> controller.handleEvent(Controller.EXIT_GAME));
        endButton.cancelButtonProperty();
        endButton.setFont(Font.font("Consolas", 20));

        buttonBox.getChildren().addAll(startButton, endButton);
        startStackPane.getChildren().add(buttonBox);

        // temp
        System.out.println(controller);
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
