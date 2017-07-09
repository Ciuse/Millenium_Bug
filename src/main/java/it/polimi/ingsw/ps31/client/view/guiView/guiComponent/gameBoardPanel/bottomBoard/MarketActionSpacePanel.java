package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 01/07/2017.
 */
public class MarketActionSpacePanel extends JPanel implements ActionListener {
    private ActionListener listener;

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    private SingleBigActionSpace[] marketActionSpace = new SingleBigActionSpace[4];

    public MarketActionSpacePanel() {
        addComponentsTopane(this);
    }

    public void addComponentsTopane(Container pane){


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0, 0, 0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0, 0,0,0,0,0,0,0};
        gbl.columnWeights = new double[]{0.11,0.12,0.12,0.14,0.11,0.13,0.17,0.10, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.3,0.136,0.136,0.136,0.02,0.136,0.136, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        marketActionSpace[0] = new SingleBigActionSpace();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        //marketActionSpace[0].setBackground(Color.BLUE);
        marketActionSpace[0].setName(valueOf(6));
        marketActionSpace[0].setPreferredSize(new Dimension(10,10));
        marketActionSpace[0].setContentAreaFilled(false);
        marketActionSpace[0].addActionListener(this);
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(marketActionSpace[0], gbc);

        marketActionSpace[1] = new SingleBigActionSpace();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        //marketActionSpace[1].setBackground(Color.RED);
        marketActionSpace[1].setName(valueOf(7));
        marketActionSpace[1].setPreferredSize(new Dimension(10,10));
        marketActionSpace[1].setContentAreaFilled(false);
        marketActionSpace[1].addActionListener(this);
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(marketActionSpace[1], gbc);

        marketActionSpace[2] = new SingleBigActionSpace();
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        //marketActionSpace[2].setBackground(Color.YELLOW);
        marketActionSpace[2].setName(valueOf(8));
        marketActionSpace[2].setPreferredSize(new Dimension(10,10));
        marketActionSpace[2].setContentAreaFilled(false);
        marketActionSpace[2].addActionListener(this);
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(marketActionSpace[2], gbc);

        marketActionSpace[3] = new SingleBigActionSpace();
        gbc.gridx = 6;
        gbc.gridy = 5;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        //marketActionSpace[3].setBackground(Color.PINK);
        marketActionSpace[3].setName(valueOf(9));
        marketActionSpace[3].setPreferredSize(new Dimension(10,10));
        marketActionSpace[3].setContentAreaFilled(false);
        marketActionSpace[3].addActionListener(this);
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(marketActionSpace[3], gbc);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }
}
