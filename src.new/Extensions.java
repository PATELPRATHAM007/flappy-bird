import javafx.stage.Stage;

public class Extensions {
    public static void setFixedWindowSize(Stage stage, int width, int height) {
        stage.setMaxHeight(height);
        stage.setMinHeight(height);
        stage.setMaxWidth(width);
        stage.setMinWidth(width);
    }
}
