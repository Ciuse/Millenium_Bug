package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 30/06/2017.
 */
public class BigActionSpacePanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private ActionSpaceBoardButton actionSpaceBoardButtonProduction;
    private ActionSpaceBoardButton actionSpaceBoardButtonHarvest;


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

        gbl.columnWeights = new double[]{0.38,0.61999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.37, 0.09, 0.27, 0.09, 0.1699, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();


        actionSpaceBoardButtonProduction = new ActionSpaceBoardButton();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        actionSpaceBoardButtonProduction.setContentAreaFilled(false);
        //actionSpaceBoardButtonProduction.setOpaque(false);
        //actionSpaceBoardButtonProduction.setBackground(Color.RED);
        pane.add(actionSpaceBoardButtonProduction, gbc);

        actionSpaceBoardButtonHarvest = new ActionSpaceBoardButton();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        actionSpaceBoardButtonHarvest.setContentAreaFilled(false);
        //actionSpaceBoardButtonHarvest.setOpaque(false);
        //actionSpaceBoardButtonHarvest.setBackground(Color.black);
        pane.add(actionSpaceBoardButtonHarvest, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
