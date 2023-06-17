import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application{
    private Stage window;
    public HomeScene homeScene;
    public RunningScene runningScene;
    public EndScene endScene;

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

        // load end scene
        endScene = new EndScene();
        endScene.setParentStage(window);

        // create controller and give access
        Controller controller = new Controller();
        controller.setGame(this);

        homeScene.setController(controller);
        runningScene.setController(controller);
        endScene.setController(controller);

        // temp
        System.out.println(controller);
    }

    // getters and setters
    public Stage getWindow() {
        return window;
    }
    public Scene getRunningScene() {
        return runningScene;
    }
    public Scene getEndScene() {
        return endScene;
    }
}
