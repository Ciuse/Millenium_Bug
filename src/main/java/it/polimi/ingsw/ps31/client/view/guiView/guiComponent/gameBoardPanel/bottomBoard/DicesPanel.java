package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 01/07/2017.
 */
public class DicesPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private SingleDicePanel[] diceLabel = new SingleDicePanel[3];

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public DicesPanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0, 0,0,0};

        gbl.columnWeights = new double[]{0.06666,0.06666,0.06666, 0.034, 0.06666,0.06666,0.06666, 0.02, 0.06666,0.06666,0.06666, 0.35, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.3333,0.3333,0.3333, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        diceLabel[0] = new SingleDicePanel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        diceLabel[0].setBackground(Color.black);
        pane.add(diceLabel[0], gbc);

        diceLabel[1] = new SingleDicePanel();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        diceLabel[1].setBackground(Color.white);
        pane.add(diceLabel[1], gbc);

        diceLabel[2] = new SingleDicePanel();
        gbc.gridx = 10;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        diceLabel[2].setBackground(Color.orange);
        pane.add(diceLabel[2], gbc);
    }

        @Override
    public void actionPerformed(ActionEvent e) {

    }
}
