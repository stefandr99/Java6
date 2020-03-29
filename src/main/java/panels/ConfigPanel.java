package panels;

import frames.MainFrame;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel;
    JSpinner sidesField;
    JComboBox colorCombo;
    JComboBox shapeCombo;
    JLabel graphLabel;
    JComboBox graphCombo;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }


    /**
     * Creez un 'text box' care indica ce face acel spinner si anume sa indice numarul de laturi ale poligonului
     * Spinnerul va permite maxim 100 de laturi si va creste cu o unitate. Setam numarul de laturi la 6 (default)
     * Am folosit combobox  pentru ca este cel mai potrivit in aceasta situatie, ni se afiseaza toate valorile si alegem pe cea pe care o dorim
     * In total avem 3 combobox-uri:
     *  - unul pentru a alege culoarea formelor (putem alege intre negru sau culoare random)
     *  - unul pentru a alege ce vrem sa desenam: poligon, elipsa sau un graf
     *  - unul pentru cazul 'graf' cu ce vrem sa desenam in acel graf: noduri sau muchii
     * Adaugam toate acestea in panoul nostru de configuratie
     */
    private void init() {
        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        sidesField.setValue(6);

        String[] colors = {"Black", "Random"};
        colorCombo = new JComboBox(colors);

        String[] shape = {"Regular Polygon", "Ellipse", "Graph"};
        shapeCombo = new JComboBox(shape);

        graphLabel = new JLabel("Graph:");
        String[] graph = {"Nodes", "Edges"};
        graphCombo = new JComboBox(graph);

        add(sidesLabel);
        add(sidesField);
        add(colorCombo);
        add(shapeCombo);
        add(graphLabel);
        add(graphCombo);
    }
}
