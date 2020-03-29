package panels;

import frames.MainFrame;
import shapes.NodeShape;
import shapes.RegularPolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.Random;


public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    private int xbegin, ybegin, xfinal, yfinal;
    BufferedImage image;
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    /**
     * Creem aplicatia si coloram fundalul panoului de desen cu alb
     */
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, W, H);
    }

    /**
     * AICI SE IMBINA ELEMENTE PENTRU COMPULSORY SI BONUS
     * Suprascriem functii la functionalitate mouse-ului astfel:
     *  - de fiecare data cand se face click se deseneaza forma
     *  - se salveaza coordonatele punctului unde se face click. Aceasta o vom folosi pentru a desena muchiile grafului
     *  - cand se elibereaza click-ul, actualizam punctul de final si desenam muchia grafului
     *  - de asemenea, mentinem up to date punctul final la miscarile mouse-ului prin suprascrierea functiei mouseDragged
     */
    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xbegin = e.getX();
                ybegin = e.getY();
                xfinal = e.getX();
                yfinal = e.getY();
                drawShape(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                graphics.drawLine(xbegin, ybegin, xfinal, yfinal);
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                xfinal = e.getX();
                yfinal = e.getY();
            }
        });
    }

    /**
     * In primele randuri facem setarile pentru cum va arata forma:
     *  - alegem random radius-ul
     *  - numarul de laturi il preluam din spinnerul config panelului
     *  - culoare o generam random daca asa este setat in configPanel sau va fi neagra
     *  - ne uitam din nou la configPanel sa vedem ce se doreste a fi desenat: poligon, elipsa sau graf (ASTA PENTRU BONUS)
     *  - pentru primele doua cazuri, se construieste obiectul dorit
     *  - pentru cazul graf, intrebam iar configPanelul ce se doreste a fi desenat: nod sau muchie
     * @param x coordonata x unde se va desena forma
     * @param y coordonata y unde se va desena forma
     */
    private void drawShape(int x, int y) {
        Random rand = new Random();
        int radius = 50 + (int) (100 * Math.random());
        int sides = (int) frame.getConfigPanel().sidesField.getValue();
        String culoare = (String) frame.getConfigPanel().colorCombo.getSelectedItem();
        Color color;
        if(culoare.compareTo("Black") == 0)
            color = new Color(0, 0, 0, 170);
        else
            color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), 100);
        graphics.setColor(color);
        String shape = (String) frame.getConfigPanel().shapeCombo.getSelectedItem();
        if(shape.compareTo("Regular Polygon") == 0)
            graphics.fill(new RegularPolygon(x, y, radius,sides));
        else if(shape.compareTo("Ellipse") == 0)
            graphics.fill(new NodeShape(x, y, radius));
        else if(shape.compareTo("Graph") == 0) {
            String graph = (String) frame.getConfigPanel().graphCombo.getSelectedItem();
            if(graph.compareTo("Nodes") == 0) {
                graphics.fill(new NodeShape(x, y, 30));
            }
            else {
            }
        }
    }

    @Override
    public void update(Graphics g) { }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
