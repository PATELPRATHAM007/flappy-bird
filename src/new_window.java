import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class App extends Application {
    @Override
    public void start(Stage BasicLayout) throws Exception {        
        BasicLayout.setTitle("flappy Bird");
        Scene fontscene = new Scene(null, 500, 350, false, null);
        BasicLayout.setScene(fontscene);
        BasicLayout.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
