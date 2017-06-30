package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 26/06/2017.
 */
public class TopBoardPanel extends JPanel {
    private ActionListener listener;
    private GuiView guiView;

    public TopBoardPanel(GuiView guiView) {
        this.guiView= guiView;
        addComponentsToPane(this);
    }


    public void attach(ActionListener listener) {
        this.listener = listener;
    }


    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0};
        gbl.rowHeights = new int[]{0,0,0};

        gbl.columnWeights = new double[]{1, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.8843, 0.286, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();


        TowerPanel jPanel1 = new TowerPanel(guiView);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        jPanel1.setOpaque(false);
       //jPanel1.setBackground(Color.RED);
        c.fill = GridBagConstraints.BOTH;
        jPanel1.setPreferredSize(new Dimension(10, 10));
        pane.add(jPanel1, c);
        jPanel1.attach(this.listener);

        CouncilPanel jPanel2 = new CouncilPanel();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        jPanel2.setOpaque(false);
        //c.fill = GridBagConstraints.BOTH;
        //jPanel2.setBackground(Color.green);
        jPanel2.setPreferredSize(new Dimension(10, 10));
        pane.add(jPanel2, c);
        jPanel2.attach(this.listener);
    }
}