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

        Image i = new Image("img/city.png");
        Image i = new Image(i, 500, 1200, false, false)
        ImageView iv = new ImageView();
        StackPane sp = new StackPane();
        sp.getChildren().add(iv);

        Scene s = new Scene(sp);
        window.setScene(s);
    }
}
