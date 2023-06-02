import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {

    public GUI() {
        JFrame frame = new JFrame(null, null);
        JPanel panel = new JPanel(null, false);
        JButton button = new JButton("hello, world!", null);

        panel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panel.setLayout(new GridLayout(1000, 100));

        frame.add(panel, BorderLayout.CENTER); 
        panel.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("flappy-bird");
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new GUI();
    }
}