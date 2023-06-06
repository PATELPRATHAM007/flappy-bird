import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class App extends Application {

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Animation");
        StackPane panel = new StackPane();

        VBox verticalContainer = new VBox();
        verticalContainer.setSpacing(10);

        Button startButton = new Button("Start");
        startButton.setPrefSize(80, 50);

        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(80, 50);

        Image backImage = new Image("city.PNG");
        ImageView backImageView = new ImageView(backImage);
        backImageView.setFitHeight(500);
        backImageView.setFitWidth(500);
        panel.getChildren().add(backImageView);


        verticalContainer.getChildren().addAll(startButton, exitButton);
        verticalContainer.setAlignment(Pos.CENTER);
        panel.getChildren().add(verticalContainer);

        startButton.prefWidthProperty().bind(window.widthProperty().multiply(0.22));
        startButton.prefHeightProperty().bind(window.heightProperty().multiply(0.1));
        exitButton.prefWidthProperty().bind(window.widthProperty().multiply(0.22));
        exitButton.prefHeightProperty().bind(window.heightProperty().multiply(0.1));
        backImageView.fitWidthProperty().bind(panel.widthProperty());
        backImageView.fitHeightProperty().bind(panel.heightProperty());
        Scene scene = new Scene(panel, 500, 500);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
