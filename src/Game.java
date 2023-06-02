public class Game {
    public static void main(String[] args) {
        openingPanel openingPanel = new openingPanel();
        Window openingWindow = new Window(openingPanel);
        openingWindow.setTitle("welcome to flappy-bird");
        try {
            Thread.sleep(1582);
        }
        catch (Exception e) {
            System.out.println("failed sleep for 1.582 seconds.");
        }
        openingWindow.dispose();
        new Window(openingPanel);


    }
}
