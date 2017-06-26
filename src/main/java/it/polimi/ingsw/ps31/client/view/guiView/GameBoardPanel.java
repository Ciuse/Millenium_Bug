package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by giulia on 25/06/2017.
 */
public class GameBoardPanel extends JPanel {
    private Image image;


    public GameBoardPanel(Image image) {
        this.image = image;
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,};
        gbl.rowHeights = new int[]{0,0};

        gbl.columnWeights = new double[]{0.469271,0.192933,0.313838,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.196296,0.803703,Double.MIN_VALUE};
        this.setLayout(gbl);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
// Disegno l'immagine sul pannello alle coordinate (0,0)
        //g.drawRect(1, 1, 400, 400);
        g.drawImage(image, 0, 0, this);
    }
}
