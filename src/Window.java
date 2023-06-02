import javax.swing.JFrame;

public class Window extends JFrame {

    public Window(Panel gamePanel) {
        // create a new blank window of size 400, 650
        setTitle("flappy bird");

        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(gamePanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Window(new Panel());
    }
}
