package it.polimi.ingsw.ps31.client.view.guiView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by giulia on 25/06/2017.
 */
public class GameBoardPanel extends PaintBackgroundPanel implements ActionListener {

    public GameBoardPanel() {
    }

    public void paintComponent(Graphics g) {
        super.imageToLoad("/gameboard_f_c.png");
        super.paintComponent(g);
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0,0,0};

        gbl.columnWeights = new double[]{0.04,0.999999,0.08,0.044,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.02,0.9743,0.06,0.2745,0.0999989898,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();


        VictoryPointTrackFirstColumnPanel jPanel1 = new VictoryPointTrackFirstColumnPanel();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        jPanel1.setBackground(Color.CYAN);
//        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel1, c);
        jPanel1.attach(this);

        VictoryPointTrackFirstRowPanel jPanel2 = new VictoryPointTrackFirstRowPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.BOTH;
        jPanel2.setBackground(Color.BLUE);
        //jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel2, c);
        jPanel2.attach(this);

        TopBoardPanel jPanel3 = new TopBoardPanel();
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        jPanel3.setBackground(Color.YELLOW);
//        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel3, c);
        jPanel3.attach(this);

        FaithPointTrackPanel jPanel4 = new FaithPointTrackPanel();
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
//        jPanel4.setBackground(Color.GREEN);
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel4, c);
        jPanel4.attach(this);

        BottomBoardPanel jPanel5 = new BottomBoardPanel();
        jPanel5.attach(this);
        c.gridx = 1;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
//        jPanel5.setBackground(Color.YELLOW);
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel5, c);
        jPanel5.attach(this);

        VictoryPointTrackSecondRowPanel jPanel6 = new VictoryPointTrackSecondRowPanel();
        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
//        jPanel6.setBackground(Color.PINK);
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel6, c);
        jPanel6.attach(this);

        MilitaryTrackPanel jPanel7 = new MilitaryTrackPanel();
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        jPanel7.setBackground(Color.black);
//        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel7, c);
        jPanel7.attach(this);

        VicttoryPointTrackSecondColumnPanel jPanel8 = new VicttoryPointTrackSecondColumnPanel();
        c.gridx = 3;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        jPanel8.setBackground(Color.RED);
        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new Dimension(10,10));
        pane.add(jPanel8, c);
        jPanel8.attach(this);

    }





    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
