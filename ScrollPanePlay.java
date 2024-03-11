package Timer;
import java.awt.*;
import javax.swing.*;

public class ScrollPanePlay extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScrollPanePlay().setVisible(true);
            }
        });
    }

    public ScrollPanePlay() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Create a JPanel with a GridLayout
        JPanel panel = new JPanel(new GridLayout(0, 3)); // any number of rows, 1 column

        // Add some labels to the panel
        panel.add(new JLabel("one"));
        panel.add(new JLabel("two"));
        panel.add(new JLabel("three"));
        panel.add(new JLabel("four"));
        panel.add(new JLabel("five"));

        // Create a JScrollPane with the panel as the viewport view
        JScrollPane scrollPane = new JScrollPane(panel);

        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);

        pack();
    }
}
