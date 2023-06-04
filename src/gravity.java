import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class gravity extends Application {
    public static final int ScreenWidth = 500;
    public static final int ScreenHeight = 500;
    public static final int line_Y = 400;
    public static final int Circle_radius = 50;
    public static final int x = line_Y - Circle_radius * 2 - 50;
    public static double y = 50 * 1.5;
    public static final double GRAVITY = 0.5;
    public static final double MAX_VELOCITY = 10;
    public static final double JUMP_VELOCITY = -10;

    private boolean isJumping = false;

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Transition");
        StackPane panel = new StackPane();

        Line line = new Line(50, line_Y, 400, line_Y);
        panel.getChildren().add(line);
        panel.setAlignment(Pos.TOP_CENTER);

        Image birdImage = new Image("bird.png");
        ImageView birdImageView = new ImageView(birdImage);
        birdImageView.setFitWidth(Circle_radius * 2);
        birdImageView.setFitHeight(Circle_radius * 2);

        StackPane birdPane = new StackPane(birdImageView);
        birdPane.setAlignment(Pos.TOP_CENTER);

        birdPane.setTranslateY(50);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), birdPane);
        transition.setByY(x);
        transition.setAutoReverse(true);
        transition.setCycleCount(TranslateTransition.INDEFINITE);
        transition.play();

        AnimationTimer gravityTimer = new AnimationTimer() {
            private double velocityY = 0;

            @Override
            public void handle(long now) {
                if (isJumping) {
                    velocityY = JUMP_VELOCITY;
                    isJumping = false;
                }

                velocityY += GRAVITY;

                if (velocityY > MAX_VELOCITY) {
                    velocityY = MAX_VELOCITY;
                }

                y += velocityY;

                if (y >= line_Y - Circle_radius * 2) {
                    y = line_Y - Circle_radius * 2;
                    velocityY = -velocityY; // Bounce back up
                }

                birdPane.setTranslateY(y);
            }
        };
        gravityTimer.start();

        panel.getChildren().add(birdPane);

        Scene scene = new Scene(panel, ScreenWidth, ScreenHeight);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                isJumping = true;
            }
        });

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
