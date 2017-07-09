package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard.SingleSmallActionSpacePanel;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 08/07/2017.
 */
public class MarketButton extends ButtonCard {
    private ActionListener listener;
    private PaintBackgroundPanel[] familyMemberPanel = new PaintBackgroundPanel[3];

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public MarketButton() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0,0,0};
        gbl.rowHeights = new int[]{0, 0,0,0,0,0};

        gbl.columnWeights = new double[]{0.05,0.44,0.02,0.44,0.05, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.05,0.44,0.02,0.44,0.05, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        familyMemberPanel[0] = new PaintBackgroundPanel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMemberPanel[0].setBackground(Color.BLUE);
        //familyMemberPanel[0].setOpaque(false);
        familyMemberPanel[0].setPreferredSize(new Dimension(10, 10));
        pane.add(familyMemberPanel[0], gbc);


        familyMemberPanel[1] = new PaintBackgroundPanel();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMemberPanel[1].setBackground(Color.RED);
        //familyMemberPanel[1].setOpaque(false);
        familyMemberPanel[1].setPreferredSize(new Dimension(10, 10));
        pane.add(familyMemberPanel[1], gbc);

        familyMemberPanel[2] = new PaintBackgroundPanel();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMemberPanel[2].setBackground(Color.PINK);
        //familyMemberPanel[2].setOpaque(false);
        familyMemberPanel[2].setPreferredSize(new Dimension(10, 10));
        pane.add(familyMemberPanel[2], gbc);

    }
}
