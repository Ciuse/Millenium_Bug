package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.model.player.FamilyMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 01/07/2017.
 */
public class SingleBigActionSpace extends JButton implements ActionListener {
    private ActionListener listener;
    private PaintBackgroundPanel[] familyMember = new PaintBackgroundPanel[6];

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public SingleBigActionSpace() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0, 0, 0, 0,0,0};

        gbl.columnWeights = new double[]{0.50,0.02,0.10666,0.02,0.10666,0.02,0.10666,0.02,0.10, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.17, 0.31, 0.04,0.31,0.17, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();


        familyMember[0] = new PaintBackgroundPanel();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[0].setBackground(Color.BLUE);
        pane.add(familyMember[0], gbc);

        familyMember[1] = new PaintBackgroundPanel();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[1].setBackground(Color.green);
        pane.add(familyMember[1], gbc);

        familyMember[2] = new PaintBackgroundPanel();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[2].setBackground(Color.pink);
        pane.add(familyMember[2], gbc);

        familyMember[3] = new PaintBackgroundPanel();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[3].setBackground(Color.black);
        pane.add(familyMember[3], gbc);

        familyMember[4] = new PaintBackgroundPanel();
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[4].setBackground(Color.yellow);
        pane.add(familyMember[4], gbc);

        familyMember[5] = new PaintBackgroundPanel();
        gbc.gridx = 6;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[5].setBackground(Color.red);
        pane.add(familyMember[5], gbc);

        }




    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
