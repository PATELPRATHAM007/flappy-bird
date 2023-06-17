import javafx.stage.Stage;

public class Controller {
    private Stage window;

    // constants
    public static final int EXIT_GAME = 0;
    public static final int START_GAME = 1;


    public int handleEvent(int event) {
        switch (event) {
            case EXIT_GAME:
                window.close();
                return 1;
            case START_GAME:
                window.setScene(null);(null);
        }
        return 0;
    }

    public void setWindow(Stage stage) {
        window = stage;
    }

    public Stage getWindow() {
        return window;
    }
}
