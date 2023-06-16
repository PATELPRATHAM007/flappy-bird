import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.*;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class App extends Application {
    private static int Screen_Width = 480;
    private static int Screen_Height = 570;
    private static int ground_width = Screen_Width + 70;
    private static int ground_height = 100;
    private static double Sub_Grass_Width = 22;
    private static double Sub_Grass_Height = 17;

    private int move = -550;
    private int i = 0;
    private int col = 0;

    private VBox pipes;
    private TranslateTransition pipeTransition;

    @Override
    public void start(Stage windowStage) throws Exception {
        windowStage.setTitle("Flappy Bird");
        windowStage.initStyle(StageStyle.UNDECORATED);

        StackPane stackPane = new StackPane();
        ImageView background = createImage("/img/xcity.jpg", 500, 450);
        stackPane.getChildren().add(background);

        Label logo = createLabelWithImage("img/logo.png", 450, 100);
        stackPane.getChildren().add(logo);
        StackPane.setAlignment(logo, Pos.CENTER);
        logo.setTranslateY(-140);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2), logo);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.08);
        scaleTransition.setToY(1.08);

        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(Timeline.INDEFINITE);
        scaleTransition.play();

        ImageView birdImageView = createImage("img/bird.png", 100, 90);
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

    private Rectangle createRectangle(double width, double height) {
        Rectangle rectangle = new Rectangle(width, height);
        // rectangle.setX(horizontal_position);
        // rectangle.setY(vertical_position);
        return rectangle;
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
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Flappy Bird");
        StackPane secondaryPanel = new StackPane();

        VBox allElement = new VBox();
        allElement.setAlignment(Pos.CENTER);
        allElement.setTranslateY(250);

        VBox imageBox = new VBox();
        ImageView city = createImage("/img/xcity.jpg", Screen_Width + 70, 496);
        imageBox.getChildren().add(city);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setTranslateY(-37);

        VBox grassBox = new VBox();
        Group grassGroup = new Group();
        Rectangle[] subRectangles = new Rectangle[25];
        while (i < 25) {
            subRectangles[i] = createRectangle(Sub_Grass_Width, Sub_Grass_Height);
            if (col == 0) {
                subRectangles[i].setFill(Color.rgb(115, 192, 46));
                col = 1;
            } else {
                subRectangles[i].setFill(Color.rgb(142, 213, 70));
                col = 0;
            }

            grassGroup.getChildren().add(subRectangles[i]);
            subRectangles[i].setTranslateX(move);
            move += Sub_Grass_Width; // Add gap between rectangles
            i++;
        }
        grassBox.getChildren().add(grassGroup);
        grassBox.setAlignment(Pos.BOTTOM_CENTER);

        VBox earthBox = new VBox();
        Rectangle ground = createRectangle(ground_width, ground_height);
        ground.setFill(Color.rgb(221, 217, 146));
        earthBox.getChildren().add(ground);
        earthBox.setAlignment(Pos.BOTTOM_CENTER);

        secondaryPanel.getChildren().add(imageBox);
        
        int[] values = new int[56];
        int range = -280;
        int j = 0;
        while (range < 280) {
            values[j] = range + 10;
            System.out.println(values[j]);
            j++;
            range += 10; // Increment the range
        }
        for (int i = 0; i < 4; i++) {
        HBox newpipes = new HBox();
        newpipes.setSpacing(30);

        VBox pipeVBox = new VBox();
        pipeVBox.setSpacing(50);


    Random r = new Random();
    int randomIndex = r.nextInt(values.length);
    int randomValue = values[randomIndex];

    ImageView pipe = createImage("/img/pipe.png", 260, 420);
    VBox.setMargin(pipe, new Insets(randomValue, 0, 0, Screen_Width + 5));
    pipeVBox.getChildren().add(pipe);

    ImageView antipipe = createImage("/img/antipipe.png", 260, 420);
    VBox.setMargin(antipipe, new Insets(0, 0, 0, Screen_Width));
    pipeVBox.getChildren().add(antipipe);

    newpipes.getChildren().add(pipeVBox);
    HBox.setMargin(pipeVBox, new Insets(0, 0, 0, i * 200)); // Adjust the spacing between newpipes horizontally
    secondaryPanel.getChildren().add(newpipes);
}

        allElement.getChildren().addAll(grassBox, earthBox);
        secondaryPanel.getChildren().add(allElement);
        StackPane.setAlignment(allElement, Pos.CENTER);

        Scene scene = new Scene(secondaryPanel, Screen_Width, Screen_Height);
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }
}