package panels;

import com.sun.xml.internal.bind.v2.TODO;
import frames.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        setLayout(new GridLayout(1, 4));
        saveBtn.addActionListener(this::save);
    }
    private void save(ActionEvent e) {
        try {
            ImageIO.write(frame.canvas.image, "PNG", new File("d:/Stefan/test.png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

}
