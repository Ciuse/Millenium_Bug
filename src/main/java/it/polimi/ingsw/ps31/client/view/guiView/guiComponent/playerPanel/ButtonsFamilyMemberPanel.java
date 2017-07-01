package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 30/06/2017.
 */
public class ButtonsFamilyMemberPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private ButtonCard buttonFamilyMember1;
    private ButtonCard buttonFamilyMember2;
    private ButtonCard buttonFamilyMember3;
    private ButtonCard buttonFamilyMember4;

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public ButtonsFamilyMemberPanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane){

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0};
        gbl.rowHeights = new int[]{0, 0,0};

        gbl.columnWeights = new double[]{0.50,0.49999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.50,0.49999, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonFamilyMember1 = new ButtonCard();
        buttonFamilyMember1.setName("1");
        buttonFamilyMember1.addActionListener(this);
        buttonFamilyMember1.setBackground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonFamilyMember1, gbc);

        buttonFamilyMember2 = new ButtonCard();
        buttonFamilyMember2.setName("2");
        buttonFamilyMember2.addActionListener(this);
        buttonFamilyMember2.setBackground(Color.orange);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonFamilyMember2, gbc);

        buttonFamilyMember3 = new ButtonCard();
        buttonFamilyMember3.setName("3");
        buttonFamilyMember3.addActionListener(this);
        buttonFamilyMember3.setBackground(Color.black);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonFamilyMember3, gbc);

        buttonFamilyMember4 = new ButtonCard();
        buttonFamilyMember4.setName("4");
        buttonFamilyMember4.addActionListener(this);
        buttonFamilyMember4.setBackground(Color.cyan);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonFamilyMember4, gbc);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
