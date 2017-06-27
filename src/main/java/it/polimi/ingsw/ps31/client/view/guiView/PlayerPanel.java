package it.polimi.ingsw.ps31.client.view.guiView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by giulia on 25/06/2017.
 */
public class PlayerPanel extends JPanel implements ActionListener {


    public PlayerPanel() throws IOException {
        addComponentsToPane(this);
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
// Disegno l'immagine sul pannello alle coordinate (0,0)
        //g.drawRect(1, 1, 400, 400);
       // g.drawImage(image, 600, 0, this);
    }

    public void addComponentsToPane(Container pane) throws IOException {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.1324, 0.556, 0.2467, 0.256, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.1324, 0.4452, 0.4452, 0.1324, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();

        PersonalBonusTilesPanel jPersonalBonusTilesPanel = new PersonalBonusTilesPanel();

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 4;
        c.gridwidth = 1;
        //c.fill = GridBagConstraints.BOTH;
        jPersonalBonusTilesPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(jPersonalBonusTilesPanel, c);
        jPersonalBonusTilesPanel.attach(this);


        BufferedImage originalImage2 = ImageIO.read(new File("C:\\Users\\giulia\\Desktop\\progetto java\\personalBoard.jpg"));
        int type2 = originalImage2.getType();
        BufferedImage resizeImage = resizeImage(originalImage2, type2, 400, 300);
        PersonalBoardPanel jPersonalBoardPanel = new PersonalBoardPanel(resizeImage);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.WEST;
        jPersonalBoardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(jPersonalBoardPanel, c);
        jPersonalBoardPanel.attach(this);

        FamilyMemberPanel jFamilyMemberPanel = new FamilyMemberPanel();

        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        //c.fill = GridBagConstraints.BOTH;
        jFamilyMemberPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(jFamilyMemberPanel, c);
        jFamilyMemberPanel.attach(this);

        LeaderCardPanel jLeaderCardPanel = new LeaderCardPanel();

        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        //c.fill = GridBagConstraints.BOTH;
        jLeaderCardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(jLeaderCardPanel, c);
        jLeaderCardPanel.attach(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width,height,type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage,0,0,width,height,null);
        g.dispose();
        return resizedImage;
    }
}
