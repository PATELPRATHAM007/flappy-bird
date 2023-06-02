import javax.swing.JFrame;

public class Window {
    private JFrame frame;
    public Window () {
        // create a new blank window of size 400, 650
        frame = new JFrame("flappy bird");

        frame.setSize(400, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
