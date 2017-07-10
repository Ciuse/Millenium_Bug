package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import it.polimi.ingsw.ps31.client.view.stateView.StateViewFamilyMember;
import it.polimi.ingsw.ps31.model.constants.DiceColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static java.lang.String.valueOf;

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
        diceLabel[0].setName("DiceColor.BLACK.name");
        diceLabel[0].setBackground(Color.black);
        pane.add(diceLabel[0], gbc);

        diceLabel[1] = new SingleDicePanel();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        diceLabel[1].setName("DiceColor.WHITE.name");
        diceLabel[1].setBackground(Color.white);
        pane.add(diceLabel[1], gbc);

        diceLabel[2] = new SingleDicePanel();
        gbc.gridx = 10;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        diceLabel[2].setName("DiceColor.ORANGE.name");
        diceLabel[2].setBackground(Color.orange);
        pane.add(diceLabel[2], gbc);
    }


    //public void setString(List<StateViewFamilyMember> stateViewFamilyMemberList){
      //  for(int i=0;i<3;i++){
        //    for (StateViewFamilyMember stateViewFamilyMember : stateViewFamilyMemberList
          //          ) {
            //    if(stateViewFamilyMember.getDiceColor().equals(diceLabel[i].getName())){
              //      diceLabel[i].getDiceLabel().getLabel().setText(valueOf(stateViewFamilyMember.getDiceValue()));
                //}
            //}
       // }
   // }

    public void setString(List<StateViewFamilyMember> list){
        for(int i=0;i<3;i++){
            Color diceColor = diceLabel[i].getBackground();
            for (StateViewFamilyMember familyMember :list
                    ) {
                if(familyMember.getDiceColor().equals(getDiceColor( diceColor))){
                    diceLabel[i].getDiceLabel().getLabel().
                            setText(valueOf(familyMember.getDiceValue()));
                }
            }

        }
    }


    public DiceColor getDiceColor(Color color){
        if(color==Color.BLACK){
            return DiceColor.BLACK;
        }if(color==Color.ORANGE){
            return DiceColor.ORANGE;
        }if(color==Color.WHITE){
            return DiceColor.WHITE;
        }else return null;
    }

        @Override
    public void actionPerformed(ActionEvent e) {

    }
}
