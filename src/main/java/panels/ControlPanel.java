package panels;

import frames.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * Setam panoul sa aiba un singur rand si 4 butoane
     * Adaugam cele 4 butoane si pentru actiunea fiecarui buton indicam ce functie sa apeleze
     */
    private void init() {
        setLayout(new GridLayout(1, 4));

        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);

        saveBtn.addActionListener(this::save2);
        loadBtn.addActionListener(this::load2);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
    }

    /**
     * E PENTRU COMPULSORY FUNCTIA ASTA
     * Salvam imaginea in documentul curent ca titlu 'test.png' (Cream acest fisier cu 'new File')
     * Prindem exceptia in cazul in care au existat probleme la 'write'
     * @param e evenimentul creat la apasarea butonului
     */
    private void save(ActionEvent e) {
        try {
            ImageIO.write(frame.getCanvas().image, "PNG", new File("./test.png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * E PENTRU OPTIONAL FUNCTIA ASTA
     * Pentru partea de salvare a fisierului am folosit aceeasi metoda ca cea de mai sus
     * Partea interesanta apare la alegerea locului unde doresc sa salvez imaginea
     * Am creat un FileChooser care la apasarea butonului save deschide o fereastra unde alegem locul unde salvez imaginea
     * Se deschide cu partitia D
     * Setam ca putem selecta doar directoare, adica nu putem salva intr un fisier imaginea :))
     * Daca am selectat directorul si totul este corect, executam comanda de scriere, adica salvare a imaginii
     * @param e evenimentul creat la apasarea butonului
     */
    private void save2(ActionEvent e) {
        try {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().createFileObject("D:"));
            jfc.setDialogTitle("Choose a directory to save your file: ");
            jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnValue = jfc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                if (jfc.getSelectedFile().isDirectory()) {
                    ImageIO.write(frame.getCanvas().image, "PNG", new File(jfc.getSelectedFile() + "/test.png"));
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * E PENTRU COMPULSORY ACEASTA FUNCTIE
     * Folosesc o functie din aceeasi familie cu cea de mai sus, pur si simplu citesc imaginea si o asignez campului 'image' din panoul de desen
     * @param e evenimentul creat la apasarea butonului
     */
    private void load(ActionEvent e) {
        try {
            frame.getCanvas().image = ImageIO.read(new File("./test.png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * E PENTRU OPTIONAL ACEASTA FUNCTIE
     * Aici este aceeasi descriere ca mai sus, cu mentiunea ca aici trebuie sa selectam fisiere, nu directoare.
     * Adica trebuie sa selectam imaginea pe care dorim sa o incarcam.
     * @param e evenimentul creat la apasarea butonului
     */
    private void load2(ActionEvent e) {
        try {
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().createFileObject("D:"));
            jfc.setDialogTitle("Choose your file: ");
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                if (jfc.getSelectedFile().isFile()) {
                    frame.getCanvas().image = ImageIO.read(new File(String.valueOf(jfc.getSelectedFile())));
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /**
     * COMPULSORY
     * Aici este functia de reset a panoului de desenat
     * Tot ce fac este sa pun peste panoul nostru o forma alba care sa 'curete' tot continutul
     * @param e evenimentul creat la apasarea butonului
     */
    private void reset(ActionEvent e) {
        frame.getCanvas().graphics.setColor(Color.WHITE);
        frame.getCanvas().graphics.fillOval(-500, -500, 2000, 2000);
    }

    /**
     * Aleg functia dispose pentru ca aceasta distruge fereastra JFrame
     * @param e evenimentul creat la apasarea butonului
     */
    private void exit(ActionEvent e) {
        frame.dispose();
    }

}
