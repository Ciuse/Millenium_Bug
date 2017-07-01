package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 01/07/2017.
 */
public class SmallActionSpace extends JPanel implements ActionListener{
    private ActionListener listener;
    private ButtonCard buttonProduction;
    private ButtonCard buttonHarvest;
    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public SmallActionSpace() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0,0,0};

        gbl.columnWeights = new double[]{0.25, 0.24, 0.50999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.35, 0.13, 0.26,0.15,0.1099, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();


        buttonProduction = new ButtonCard();
        buttonProduction.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //buttonProduction.setOpaque(false);
        buttonProduction.setBackground(Color.RED);
        pane.add(buttonProduction,gbc);

        buttonHarvest = new ButtonCard();
        buttonHarvest.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //buttonHarvest.setOpaque(false);
        buttonHarvest.setBackground(Color.BLUE);
        pane.add(buttonHarvest,gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
