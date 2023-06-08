import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ground extends Application {

    private static int Screen_Width = 480;
    private static int Screen_Height = 620;
    private static int ground_width = Screen_Width;
    private static int ground_height = 100;
    private static int Sub_Grass_Width = 31;
    private static int Sub_Grass_Height = 17;

    private int move = -310;
    private int i = 0;
    private int col = 0;

    @Override
    public void start(Stage windowStage) throws Exception {
        windowStage.setTitle("ground");

        StackPane stackPane = new StackPane();
        Group grassGroup = new Group();

        Image cityImage = Addimage("img/city.png");
        ImageView cityImageView = viewImage(cityImage);
        ImageSize(cityImageView, Screen_Width, 563);
        stackPane.setAlignment(cityImageView,Pos.TOP_CENTER);
        stackPane.getChildren().addAll(cityImageView);


        Rectangle ground = createRectangle(ground_width, ground_height);
        ground.setFill(Color.rgb(221,217, 146));
        stackPane.setAlignment(ground,Pos.BOTTOM_CENTER);
    

        Rectangle[] sub_rectangles = new Rectangle[20];
        while (i < 20) {
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
        stackPane.setAlignment(grassGroup,Pos.BOTTOM_CENTER);
        grassGroup.setTranslateY(-(ground_height));
        stackPane.getChildren().addAll(ground, grassGroup);
        Scene scene = new Scene(stackPane, Screen_Width, Screen_Height);
        windowStage.setScene(scene);
        windowStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private Rectangle createRectangle(int width, int height) {
        Rectangle rectangle = new Rectangle(width, height);
        return rectangle;
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
