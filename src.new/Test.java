import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Test extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("flappy-bird");
        Extensions.setFixedWindowSize(window, 900, 600);
        window.show();

        ImageView iv = new ImageView(new Image("img/bird.png"));
        StackPane sp = new StackPane();
        sp.getChildren().add(iv);

        Scene s = new Scene(sp);
        window.setScene(s);
    }
}
