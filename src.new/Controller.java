public class Controller {
    private Game game;

    // constants
    public static final int EXIT_GAME = 0;
    public static final int START_GAME = 1;
    public static final int END_SCREEN = 2;


    public int handleEvent(int event) {
        switch (event) {
            case EXIT_GAME:
                game.getWindow().close();
                return 1;
            case START_GAME:
                game.getWindow().setScene(game.getRunningScene());
                return 1;
            case END_SCREEN:
                game.getWindow().setScene(game.getEndScene());
                return 1;
        }
        return 0;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
