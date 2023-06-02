import javax.swing.JButton;

public class Game {
    public static void main(String[] args) {
        Panel openingPanel = new Panel();
        Window openingWindow = new Window(openingPanel);
        openingWindow.setTitle("welcome to flappy-bird");
        try {
            Thread.sleep(1582);
        }
        catch (Exception e) {
            System.out.println("failed sleep for 1.582 seconds.");
        }
        openingWindow.dispose();


        Panel panel = new Panel();
        JButton playButton = new JButton("play");
        panel.add(playButton);
        new Window(panel);
    }
}
