package frames;

import panels.ControlPanel;
import panels.ConfigPanel;
import panels.DrawingPanel;

import javax.swing.*;

import java.awt.*;

import static javax.swing.SwingConstants.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    /**
     * Constructorul clasei care da nume aplicatiei (numele din banda) si apeleaza functia init care creeaza tot frame-ul
     */
    public MainFrame() {
        super("My Drawing Application");
        init();
    }


    /**
     * functie care returneaza pagina de Draw, canvasul
     * @return canvasu-ul
     */
    public DrawingPanel getCanvas() {
        return canvas;
    }

    /**
     *
     * @return panoul de configuratie a desenului
     */
    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    /**
     * setDefaultCloseOperation(EXIT_ON_CLOSE); -- indica faptul ca se inchide aplicatia la apasarea exit-ului
     * Creez cele 3 panouri ale aplicatiei si apoi le adaug in fereastra in pozitiile corespunzatoare
     */
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);
        configPanel = new ConfigPanel(this);

        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(configPanel, BorderLayout.NORTH);

        pack();
        }
}
