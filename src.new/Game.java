import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application{
    private Stage window;
    private HomeScene homeScene;
    private RunningScene runningScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // create window
        window = stage;
        window.setTitle("flappy-bird");
        Extensions.setFixedWindowSize(window, 900, 600);
        window.show();

        // load home scene
        homeScene = new HomeScene();
        homeScene.setParentStage(window);

        // set initial scene
        window.setScene(homeScene);

        // load running scene
        runningScene = new RunningScene();
        runningScene.setParentStage(window);

        // create controller and give access
        Controller controller = new Controller();
        controller.setWindow(window);

        homeScene.setController(controller);
    }

    public Stage getWindow() {
        return window;
    }
}
