import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Flappy Bird");

        StackPane stackContainer = new StackPane();
        String backgroundImage = getClass().getResource("city.png").toExternalForm();

        stackContainer.setStyle("-fx-background-image: url('" + backgroundImage + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-position: center center;");

        Image logo = new Image(getClass().getResourceAsStream("flappy bird loco.png"));
        ImageView logoView = new ImageView(logo);
        stackContainer.getChildren().add(logoView);

        logoView.fitWidthProperty().bind(stackContainer.widthProperty().multiply(0.6));
        logoView.fitHeightProperty().bind(stackContainer.heightProperty().multiply(0.2));

        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        logoView.setTranslateY(40);

        Image birdImage = new Image(getClass().getResourceAsStream("bird.png"));
        ImageView birdImageView = new ImageView(birdImage);
        StackPane.setAlignment(birdImageView, Pos.CENTER);
        logoView.setTranslateY(40);
        birdImageView.setFitWidth(80);
        birdImageView.setFitHeight(60);
        birdImageView.setTranslateY(-20);
        stackContainer.getChildren().add(birdImageView);

        Button startButton = new Button("Start");
        startButton.setPrefWidth(150);
        startButton.setPrefHeight(35);
        startButton.setOnAction(event -> {
            // TODO: Implement start button logic
            System.out.println("Start button clicked");
        });

        
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> window.close()); 
        exitButton.setPrefWidth(150);
        exitButton.setPrefHeight(35);
        VBox buttonContainer = new VBox(20); 
        buttonContainer.getChildren().addAll(startButton, exitButton);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setTranslateY(65);
        stackContainer.getChildren().add(buttonContainer);

        Scene windowScene = new Scene(stackContainer, 500, 350);
        window.setScene(windowScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
