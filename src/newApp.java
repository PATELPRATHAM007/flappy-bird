import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class newApp extends Application {
    private static int Screen_Width = 480;
    private static int Screen_Height = 570;
    private static int ground_width = Screen_Width + 70;
    private static int ground_height = 100;
    private static double Sub_Grass_Width = 22;
    private static double Sub_Grass_Height = 17;

    private int move = -550;
    private int i = 0;
    private int col = 0;
    @Override
    public void start(Stage windowStage) throws Exception {
        windowStage.setTitle("Flappy Bird");
        windowStage.initStyle(StageStyle.UNDECORATED);

        StackPane stackPane = new StackPane();
        ImageView background = createImage("xcity.jpg", 500, 450);
        stackPane.getChildren().add(background);

        Label logo = createLabelWithImage("logo.png", 450, 100);
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

    private Rectangle createRectangle( double width, double height) {
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
        Stage SecondaryStage = new Stage();
        SecondaryStage.setTitle("Flappy Bird");
        VBox All_Element = new VBox(); 
        
        
        VBox imageBox = new VBox();
        ImageView city = createImage("xcity.jpg", Screen_Width + 70,457);
        imageBox.getChildren().add(city);
        imageBox.setAlignment(Pos.TOP_CENTER);
        
        // ImageView pipe = createImage("pipe.png", 200, Screen_Height);
        // StackPane.setAlignment(pipe, Pos.TOP_CENTER);
        // secondStackPane.getChildren().add(pipe);

        VBox grassbBox = new VBox();
        Group grassGroup = new Group();
        Rectangle[] sub_rectangles = new Rectangle[25];
        while (i < 25) {
            sub_rectangles[i] = createRectangle(Sub_Grass_Width, Sub_Grass_Height);
            if (col == 0) {
                sub_rectangles[i].setFill(Color.rgb(115, 192, 46));
                col = 1;
            } else {
                sub_rectangles[i].setFill(Color.rgb(142, 213, 70));
                col = 0;
            }

            grassGroup.getChildren().add(sub_rectangles[i]);
            sub_rectangles[i].setTranslateX(move);
            move += Sub_Grass_Width; // Add gap between rectangles
            i++;
        }
        grassbBox.getChildren().add(grassGroup);
        grassbBox.setAlignment(Pos.BOTTOM_CENTER);

        VBox earthbox = new VBox();
        Rectangle ground = createRectangle(ground_width,ground_height);
        ground.setFill(Color.rgb(221,217, 146));
        earthbox.getChildren().add(ground);
        earthbox.setAlignment(Pos.BOTTOM_CENTER);

        All_Element.getChildren().addAll(imageBox,grassbBox,earthbox);

        Button closeButton = createButton("close", 150, 50);
        closeButton.setOnAction(Event -> SecondaryStage.close());   
        
        

        Scene scene = new Scene(All_Element, Screen_Width, Screen_Height);
        SecondaryStage.setScene(scene);
        SecondaryStage.show();
    }
}
