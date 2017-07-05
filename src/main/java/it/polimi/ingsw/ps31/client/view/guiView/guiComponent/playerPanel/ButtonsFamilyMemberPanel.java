package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.stateView.StateViewFamilyMember;
import it.polimi.ingsw.ps31.model.constants.DiceColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 30/06/2017.
 */
public class ButtonsFamilyMemberPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private FamilyMemberButton[] buttonFamilyMember = new FamilyMemberButton[4];


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

        buttonFamilyMember[0] = new FamilyMemberButton();
        buttonFamilyMember[0].setName("1");
        buttonFamilyMember[0].addActionListener(this);
        buttonFamilyMember[0].setBackground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonFamilyMember[0], gbc);

        buttonFamilyMember[1] = new FamilyMemberButton();
        buttonFamilyMember[1].setName("2");
        buttonFamilyMember[1].addActionListener(this);
        buttonFamilyMember[1].setBackground(Color.orange);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonFamilyMember[1], gbc);

        buttonFamilyMember[2] = new FamilyMemberButton();
        buttonFamilyMember[2].setName("3");
        buttonFamilyMember[2].addActionListener(this);
        buttonFamilyMember[2].setBackground(Color.black);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonFamilyMember[2], gbc);

        buttonFamilyMember[3] = new FamilyMemberButton();
        buttonFamilyMember[3].setName("4");
        buttonFamilyMember[3].addActionListener(this);
        buttonFamilyMember[3].setBackground(Color.cyan);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(buttonFamilyMember[3], gbc);


    }

    public void printMyFamilyMembersOnPlayerPanel(StateViewFamilyMember stateViewFamilyMember) {
        if (stateViewFamilyMember.getActionSpaceId() != -1) {
            DiceColor colorFamilyMember = stateViewFamilyMember.getDiceColor();
            for (int i = 0; i < 4; i++) {
                if (colorFamilyMember.equals(this.getFamilyMemberColor(buttonFamilyMember[i].getBackground()))) {
                    buttonFamilyMember[i].setEnabled(false);
                }

            }
        }
    }

    public DiceColor getFamilyMemberColor(Color color){
        if(color==Color.BLACK){
            return DiceColor.BLACK;
        }if(color==Color.ORANGE){
            return DiceColor.ORANGE;
        }if(color==Color.WHITE){
            return DiceColor.WHITE;
        }if(color==Color.CYAN){
            return DiceColor.NEUTRAL;
        }else return null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
