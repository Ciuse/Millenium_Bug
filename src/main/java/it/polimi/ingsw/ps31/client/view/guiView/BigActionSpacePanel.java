package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 30/06/2017.
 */
public class BigActionSpacePanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private SingleBigActionSpace singleBigActionSpaceProduction;
    private SingleBigActionSpace singleBigActionSpaceHarvest;


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public BigActionSpacePanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0};
        gbl.rowHeights = new int[]{0, 0, 0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.50,0.50, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.35, 0.13, 0.26, 0.15, 0.11, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();


        singleBigActionSpaceProduction = new SingleBigActionSpace();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //singleBigActionSpaceProduction.setOpaque(false);
        singleBigActionSpaceProduction.setBackground(Color.RED);
        pane.add(singleBigActionSpaceProduction, gbc);

        singleBigActionSpaceHarvest = new SingleBigActionSpace();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //singleBigActionSpaceHarvest.setOpaque(false);
        singleBigActionSpaceHarvest.setBackground(Color.black);
        pane.add(singleBigActionSpaceHarvest, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
