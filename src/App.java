import java.util.ArrayList;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    public static final double MAX_VELOCITY = 10;
    public static final double JUMP_VELOCITY = -8;

    private int birdWidth = 60;
    private int birdHeight = 50;

    private boolean isJumping = false;
    private double velocityY = 0.0;
    private boolean gameEnded = false;
    private int score = 0;
    private ArrayList<ImageView> successfulPipes = new ArrayList<ImageView>();

    private static final int PIPE_WIDTH = 250;
    // private static final int PIPE_HEIGHT = 400;
    private static final int PIPE_GAP = 140;
    private static int PIPE_SPEED = 2;

    private Group pipeGroup;
    private Timeline pipeTimeline;
    private ImageView birdImageView;
    private Label ScoreLabel;

    @Override
    public void start(Stage first_Stage) throws Exception {
        //set the title for frist_stage
        first_Stage.setTitle("Flappy Bird");
        //create pane for adding element
        Pane panel = new Pane();

        //adding background image
        ImageView background = createImageView("/img/xcity.jpg", 0, 0, 500, 450);
        panel.getChildren().add(0, background);

        // adding flappy bird logo
        Label logo = createLabelWithImage("img/logo.png", 450, 100);
        panel.getChildren().add(logo);
        //set logo position
        logo.setLayoutX(20);
        logo.setLayoutY(10);

        //set scallertransion to the logo
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2), logo);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.08);
        scaleTransition.setToY(1.08);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(Timeline.INDEFINITE);
        scaleTransition.play();

        //add bird image
        birdImageView = createImageView("img/bird.png", 200, 120, 100, 90);
        panel.getChildren().add(birdImageView);

        //add gridpane for button
        GridPane buttonGridPane = createGridPane(10, 20);

        //start button for start game
        Button startButton = createButton("Start", 200, 60);

        //for the opening flappy bird game screen
        startButton.setOnAction(event -> {
            gameEnded = false;
            secondStage();
        });

        //for exit the game
        Button endButton = createButton("End", 200, 60);
        endButton.setOnAction(event -> first_Stage.close());

        //horizontally set button
        buttonGridPane.add(startButton, 0, 0);
        buttonGridPane.add(endButton, 0, 1);
        panel.getChildren().add(buttonGridPane);
        
        //set gridpane in screen layout
        buttonGridPane.setLayoutX(150);
        buttonGridPane.setLayoutY(250);

        //create scene object for show the element in screen
        Scene screen = new Scene(panel, 500, 450);
        //set the screen on the stage
        first_Stage.setScene(screen);
        //for show the stage
        first_Stage.show();
    }

    private void secondStage() {
        Stage game_stage = new Stage(); // cerate new stage
        game_stage.setTitle("Flappy Bird"); //set title for game_stage
        Pane panel = new Pane();//create panel for adding element

        //create the background image
        ImageView background = createImageView("/img/xcity.jpg", 0, 0, 480, 500);
        panel.getChildren().add(background);

        //create pipe for take upper pipe and lower pipe
        pipeGroup = new Group();
        panel.getChildren().add(pipeGroup);
        pipeTimeline = new Timeline();
        //use the create pipe method for maeking pipe
        createPipes();
        //create pipetimeline for handleng moving pipe animation 
        pipeTimeline.setCycleCount(Animation.INDEFINITE);
        pipeTimeline.setAutoReverse(false);

        //create bird image
        birdImageView = createImageView("/img/bird.png", 210, 120, 60, 50);
        panel.getChildren().add(birdImageView);

        birdImageView.setOnKeyPressed(event -> {
            //if player chick the space button than bird will be jump
            if (event.getCode() == KeyCode.SPACE) {
                //use the jump method for pushing bird in upwared direction 
                jump();
            }
        });
        //allow the event
        birdImageView.setFocusTraversable(true);

        //make animationtimer for checking bird postion like bird was collide with pipe or not and also check bird was collide will earth or not
        AnimationTimer birdAnimationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //use below function for checkingbird position
                updateBirdPosition();
            }
        };
        //start animation timer 
        birdAnimationTimer.start();

        //create the earth 
        ImageView earth = createImageView("/img/earth.png", 0, 500, 480, 130);
        panel.getChildren().add(earth);

        //create score lable for display the score on screen
        ScoreLabel = new Label("" + score);
        ScoreLabel.setStyle("-fx-font-size: 80px; -fx-font-family: Arial; -fx-text-fill: white;");
        ScoreLabel.setLayoutX(215); // Adjust the X position
        ScoreLabel.setLayoutY(30); // Adjust the y position

        //making dropshadow for score text boarder
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(2);
        dropShadow.setSpread(2);
        ScoreLabel.setEffect(dropShadow); // set this on lable text
        panel.getChildren().add(ScoreLabel);
        Scene screen = new Scene(panel, 480, 620);
        game_stage.setScene(screen); 
        game_stage.show();
        pipeTimeline.play();
    }

    private void jump() {
        velocityY = JUMP_VELOCITY;
        isJumping = true;
    }

    private void updateBirdPosition() {
        if (!gameEnded) {
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
        }

        // Check collision with pipes
        for (int i = 0; i < pipeGroup.getChildren().size(); i += 2) {
            ImageView upperPipe = (ImageView) pipeGroup.getChildren().get(i + 0);
            ImageView lowerPipe = (ImageView) pipeGroup.getChildren().get(i + 1);
            if (upperPipe.getX() + 62 > birdImageView.getX() && upperPipe.getX() < birdImageView.getX() + birdWidth) {
                if (upperPipe.getY() + 2000 > y) {
                    gameEnded = true;
                    PIPE_SPEED = 0;
                } else if (lowerPipe.getY() < y + birdHeight) {
                    gameEnded = true;
                    PIPE_SPEED = 0;
                    System.out.println("game ended!");
                }
            } else if (upperPipe.getX() + 62 < birdImageView.getX()) {
                boolean breaked = false;
                for (int j = 0; j < successfulPipes.size(); j++) {
                    if (successfulPipes.get(j) == upperPipe) {
                        breaked = true;
                        break;
                    }
                }
                if (!breaked) {
                    score++;
                    ScoreLabel.setText("" + score);
                    successfulPipes.add(upperPipe);
                }
            }
        }
        System.out.println(score);
        //
    }

    private void createPipes() {
        for (int i = 0; i < 3; i++) {

            double GAPX = 2000 + i * 300;
            double GAP = 200 + 80 * Math.random();
            double GAPY = Math.random() * (500 - PIPE_GAP); // Random y-position of the pipe opening

            ImageView upperPipe = createImageView("/img/down_pipe.png", GAPX, GAPY - 2000); // uses h=2000, w=62 of
                                                                                            // image
            ImageView lowerPipe = createImageView("/img/up_pipe.png", GAPX, GAPY + GAP);

            pipeGroup.getChildren().addAll(upperPipe, lowerPipe);

            KeyFrame kf = new KeyFrame(Duration.millis(10), event -> {
                // Move the pipes to the left
                upperPipe.setX(upperPipe.getX() - PIPE_SPEED);
                lowerPipe.setX(lowerPipe.getX() - PIPE_SPEED);

                // Check if the pipe is off the screen, then reset its position
                if (upperPipe.getX() + PIPE_WIDTH < 0) {
                    successfulPipes.remove(upperPipe);
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
