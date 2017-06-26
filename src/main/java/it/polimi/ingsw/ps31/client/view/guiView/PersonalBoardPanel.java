package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by giulia on 25/06/2017.
 */
public class PersonalBoardPanel extends JPanel {
    private Image image;

    public PersonalBoardPanel() {
        ImageIcon image2 = new ImageIcon("C:\\Users\\giulia\\Desktop\\progetto java\\Lorenzo_Punchboard_FRONT_compressed\\punchboard_f_c_04.jpg");
    }

    public PersonalBoardPanel(Image image) {

        this.image = image;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
// Disegno l'immagine sul pannello alle coordinate (0,0)
        //g.drawRect(1, 1, 400, 400);
        g.drawImage(image, 600, 0, this);
    }
}
