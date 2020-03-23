package frames;

import com.sun.deploy.panel.ControlPanel;
import panels.ConfigPanel;
import panels.DrawingPanel;

import javax.swing.*;

import static javax.swing.SwingConstants.CENTER;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    public MainFrame() {
        super("My Drawing Application");
        init();
    }
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new DrawingPanel(this);
        add(canvas, CENTER);
        pack();
        }
}
