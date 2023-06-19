import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    public static final int ScreenWidth = 500;
    public static final int ScreenHeight = 550;
    public static final int line_Y = 400;
    public static final int Circle_radius = 50;
    public static final int x = line_Y - Circle_radius * 2 - 50;
    public static double y = 50 * 1.5;
    public static final double GRAVITY = 0.9;
    public static final double MAX_VELOCITY = 10 ;
    public static final double JUMP_VELOCITY = -12;

    private int birdWidth = 60;
    private int birdHeight = 50;

    private boolean isJumping = false;
    private double velocityY = 0.0;
    private boolean gameEnded = false;
    private boolean printed = false;
    private int tempCount = 0;

    private static final int PIPE_WIDTH = 250;
    // private static final int PIPE_HEIGHT = 400;
    private static final int PIPE_GAP = 140;
    private static int PIPE_SPEED = 2;

    private Group pipeGroup;
    private Timeline pipeTimeline;
    private ImageView birdImageView;

    @Override
    public void start(Stage first_Stage) throws Exception {
        first_Stage.setTitle("Flappy Bird");
        Pane panel = new Pane();

        ImageView background = createImageView("/img/xcity.jpg", 0, 0, 500, 450);
        panel.getChildren().add(0, background);

        Label logo = createLabelWithImage("img/logo.png", 450, 100);
        panel.getChildren().add(logo);
        logo.setLayoutX(20);
        logo.setLayoutY(10);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2), logo);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.08);
        scaleTransition.setToY(1.08);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(Timeline.INDEFINITE);
        scaleTransition.play();

        birdImageView = createImageView("img/bird.png", 200, 120, birdWidth, birdHeight);
        panel.getChildren().add(birdImageView);

        GridPane buttonGridPane = createGridPane(10, 20);

        Button startButton = createButton("Start", 200, 60);
        startButton.setOnAction(event -> {
            gameEnded = false;
            secondStage();
        });

        Button endButton = createButton("End", 200, 60);
        endButton.setOnAction(event -> first_Stage.close());

        buttonGridPane.add(startButton, 0, 0);
        buttonGridPane.add(endButton, 0, 1);

        panel.getChildren().add(buttonGridPane);
        buttonGridPane.setLayoutX(150);
        buttonGridPane.setLayoutY(250);

        Scene screen = new Scene(panel, 500, 450);
        first_Stage.setScene(screen);
        first_Stage.show();
    }

    private void secondStage() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Flappy Bird");
        Pane panel = new Pane();

        ImageView background = createImageView("/img/xcity.jpg", 0, 0, 480, 500);
        panel.getChildren().add(background);


        pipeGroup = new Group();
        panel.getChildren().add(pipeGroup);
        pipeTimeline = new Timeline();
        createPipes();

        pipeTimeline.setCycleCount(Animation.INDEFINITE);
        pipeTimeline.setAutoReverse(false);

        birdImageView = createImageView("/img/bird.png", 210, 120, 60, 50);
        panel.getChildren().add(birdImageView);

        birdImageView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                jump();
            }
        });

        birdImageView.setFocusTraversable(true);

        AnimationTimer birdAnimationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateBirdPosition();
            }
        };

        birdAnimationTimer.start();


        ImageView earth = createImageView("/img/earth.png", 0, 500, 480, 130);
        panel.getChildren().add(earth);

        Scene screen = new Scene(panel, 480, 620);
        primaryStage.setScene(screen);
        primaryStage.show();
        pipeTimeline.play();
    }

    private void jump() {
        velocityY = JUMP_VELOCITY;
        isJumping = true;
    }

    private void updateBirdPosition() {
        if (isJumping) {
            velocityY += GRAVITY;
            velocityY = Math.min(velocityY, MAX_VELOCITY);
            y += velocityY;
        }

        // Adjust bird position
        birdImageView.setY(y);

        // Check collision with ground
        if (y + Circle_radius * 2 >= ScreenHeight) {
        // Bounce the bird back up
        y = ScreenHeight - Circle_radius * 2;
        velocityY = JUMP_VELOCITY;
        isJumping = false;
    }

        // Check collision with pipes
        for (int i = 0; i < pipeGroup.getChildren().size(); i += 2) {
            ImageView upperPipe = (ImageView) pipeGroup.getChildren().get(i + 0);
            ImageView lowerPipe = (ImageView) pipeGroup.getChildren().get(i + 1);
            if (!printed) {
                System.out.println("upperpipe");
                System.out.println(upperPipe);
                System.out.println("lowerpipe");
                System.out.println(lowerPipe);
            }
            if (upperPipe.getX() + 62 > birdImageView.getX() && upperPipe.getX() < birdImageView.getX() + birdWidth) {
                if (upperPipe.getY() + 2000 > y) {
                    gameEnded = true;
                    PIPE_SPEED = 0;
                }
                else if (lowerPipe.getY() < y + birdHeight) {
                    gameEnded = true;
                    PIPE_SPEED = 0;
                    System.out.println("game ended!");
                }
            }
            // System.out.println(upperPipe.getX() + PIPE_WIDTH < birdImageView.getX() + birdWidth);
        }
        printed = true;
        //
    }

    private void createPipes() {
        for (int i = 0; i < 3; i++) {
            // double pipeX = 400 + i * 300; // Initial x-position of the pipe
            // double pipeY = Math.random() * (500 - PIPE_GAP); // Random y-position of the pipe opening

            double GAPX = 2000 + i * 300;
            double GAP = 200 + 80 * Math.random();
            double GAPY = Math.random() * (500 - PIPE_GAP); // Random y-position of the pipe opening


            // ImageView upperPipe = createImageView("/img/pipe.png", pipeX, 0, PIPE_WIDTH, 200);
            // ImageView lowerPipe = createImageView("/img/antipipe.png", pipeX, pipeY + PIPE_GAP, PIPE_WIDTH, 500 - pipeY - PIPE_GAP);

            ImageView upperPipe = createImageView("/img/down_pipe.png", GAPX, GAPY - 2000); // uses h=2000, w=62 of image
            ImageView lowerPipe = createImageView("/img/up_pipe.png", GAPX, GAPY + GAP);


            pipeGroup.getChildren().addAll(upperPipe, lowerPipe);

            KeyFrame kf = new KeyFrame(Duration.millis(10), event -> {
                // Move the pipes to the left
                upperPipe.setX(upperPipe.getX() - PIPE_SPEED);
                lowerPipe.setX(lowerPipe.getX() - PIPE_SPEED);

                // Check if the pipe is off the screen, then reset its position
                if (upperPipe.getX() + PIPE_WIDTH < 0) {
                    double newX = pipeGroup.getChildren().stream()
                            .filter(node -> node instanceof ImageView)
                            .map(node -> (ImageView) node)
                            .mapToDouble(ImageView::getX)
                            .max()
                            .orElse(0) + 300;

                    upperPipe.setX(newX);
                    lowerPipe.setX(newX);

                    // double nGAPX = newX;
                    double nGAP = 200 + 80 * Math.random();
                    double nGAPY = Math.random() * (500 - PIPE_GAP); 

                    // double newY = Math.random() * (500 - PIPE_GAP);
                    upperPipe.setY(nGAPY - 2000);
                    upperPipe.setFitHeight(2000);
                    lowerPipe.setY(nGAP + nGAPY);
                    lowerPipe.setFitHeight(2000);
                }
            });

            pipeTimeline.getKeyFrames().add(kf);
        }
    }

    private ImageView createImageView(String imageUrl, double x, double y, double width, double height) {
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

    private ImageView createImageView(String imageUrl, double x, double y) {
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        return imageView;
    }

    private Label createLabelWithImage(String imageUrl, double width, double height) {
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        Label label = new Label("", imageView);
        label.setPrefSize(width, height);
        return label;
    }

    private Button createButton(String text, double width, double height) {
        Button button = new Button(text);
        button.setPrefSize(width, height);
        return button;
    }

    private GridPane createGridPane(double hGap, double vGap) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(hGap);
        gridPane.setVgap(vGap);
        return gridPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
