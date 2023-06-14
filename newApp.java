import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class newApp extends Application {

    @Override
    public void start(Stage windowStage) throws Exception {
        windowStage.setTitle("Flappy Bird");
        windowStage.initStyle(StageStyle.UNDECORATED);

        StackPane stackPane = new StackPane();
        ImageView background = createImage("city.png", 500, 450);
        stackPane.getChildren().add(background);

        Label logo = createLabelWithImage("logo.png", 450, 100);
        stackPane.getChildren().add(logo);
        StackPane.setAlignment(logo, Pos.CENTER);
        logo.setTranslateY(-140);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2), logo);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);

        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(Timeline.INDEFINITE);
        scaleTransition.play();

        ImageView birdImageView = createImage("bird.png", 100, 90);
        stackPane.getChildren().add(birdImageView);
        StackPane.setAlignment(birdImageView, Pos.CENTER);
        birdImageView.setTranslateY(-30);

        GridPane buttonGridPane = createGridPane(10, 10);

        Button startButton = createButton("Start", 200, 60);
        startButton.setOnAction(event -> SecondaryStage());

        Button endButton = createButton("End", 200, 60);
        endButton.setOnAction(event -> windowStage.close());

        buttonGridPane.add(startButton, 0, 0);
        buttonGridPane.add(endButton, 0, 1);

        stackPane.getChildren().add(buttonGridPane);
        buttonGridPane.setAlignment(Pos.CENTER);
        buttonGridPane.setTranslateY(110);

        Scene scene = new Scene(stackPane, 480, 450);
        windowStage.setScene(scene);
        windowStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private GridPane createGridPane(double horizontalGap, double verticalGap) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(horizontalGap);
        gridPane.setVgap(verticalGap);
        return gridPane;
    }

    private Button createButton(String buttonName, double buttonWidth, double buttonHeight) {
        Button button = new Button(buttonName);
        button.setPrefWidth(buttonWidth);
        button.setPrefHeight(buttonHeight);
        return button;
    }

    private Label createLabelWithImage(String file, double width, double height) {
        ImageView imageView = createImage(file, width, height);
        Label label = new Label();
        label.setGraphic(imageView);

        return label;
    }

    private ImageView createImage(String file, double width, double height) {
        ImageView imageView = new ImageView(new Image(file));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    private void SecondaryStage() {
        Stage SecondaryStage = new Stage();
        

        SecondaryStage.setTitle("New Stage");
        
        StackPane secondStackPane = new StackPane();

        Button closeButton = createButton("close", 100, 100);
        closeButton.setOnAction(Event -> SecondaryStage.close());
        secondStackPane.getChildren().add(closeButton);     

        

        Scene scene = new Scene(secondStackPane, 480, 620);
        SecondaryStage.setScene(scene);
        SecondaryStage.show();
    }
}
