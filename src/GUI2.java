import javax.swing.JFrame;

public class GUI2 {
    private JFrame frame;
    public GUI2 () {
        frame = new JFrame("flappy bird");
        frame.setSize(300, 600);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI2();
    }
}
