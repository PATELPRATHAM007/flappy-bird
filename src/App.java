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
        
        String backgroundImage = getClass().getResource("img/city.png").toExternalForm();

        stackContainer.setStyle("-fx-background-image: url('" + backgroundImage + "'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-position: center center;");

                
        Image logo = Addimage("img/logo.png");
        ImageView logoView = viewImage(logo);

        stackContainer.getChildren().add(logoView);

        logoView.fitWidthProperty().bind(stackContainer.widthProperty().multiply(0.6));
        logoView.fitHeightProperty().bind(stackContainer.heightProperty().multiply(0.2));

        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
        logoView.setTranslateY(40);

        Image birdImage = Addimage("img/bird.png");
        ImageView birdImageView = viewImage(birdImage);
        
        StackPane.setAlignment(birdImageView, Pos.CENTER);
        ImageSize(birdImageView, 80, 60);
        birdImageView.setTranslateY(-20);
        stackContainer.getChildren().add(birdImageView);

        Button startButton = CreateButton("Start", 150, 35);
        
        startButton.setOnAction(event -> {
            // TODO: Implement start button logic
            System.out.println("Start button clicked");
        });

        
        Button exitButton = CreateButton("exit", 150, 35);
        exitButton.setOnAction(event -> window.close()); 

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
    private Button CreateButton(String string , double width,double hight)
    {
        Button button = new Button(string);
        button.setPrefHeight(hight);
        button.setPrefWidth(width);
        return button;
    }
    private Image Addimage(String file)
    {
        Image image = new Image(getClass().getResourceAsStream(file));
        return image;
    }
    private ImageView viewImage(Image image)
    {
        ImageView imageView = new ImageView(image);
        return imageView;
    }
    private void ImageSize(ImageView image,double width,double hight)
    {
        image.setFitWidth(width);
        image.setFitHeight(hight);
    }
}