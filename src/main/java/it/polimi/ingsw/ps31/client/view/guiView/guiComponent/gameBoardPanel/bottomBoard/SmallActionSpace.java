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
    private MarketButton[] buttons = new MarketButton[2];
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


        buttons[0] = new MarketButton();
        buttons[0].addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        buttons[0].setName("buttonProduction");
        buttons[0].setContentAreaFilled(false);
        buttons[0].setOpaque(false);
        //buttons[0].setBackground(Color.RED);
        pane.add(buttons[0],gbc);

        buttons[1] = new MarketButton();
        buttons[1].addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        buttons[1].setName("buttonHarvest");
        buttons[1].setContentAreaFilled(false);
        //buttons[1].setOpaque(false);
        buttons[1].setBackground(Color.BLUE);
        pane.add(buttons[1],gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
