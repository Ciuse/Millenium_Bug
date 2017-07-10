package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewFamilyMember;
import it.polimi.ingsw.ps31.model.constants.DiceColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by giulia on 01/07/2017.
 */
public class DicesPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private PaintBackgroundPanel[] diceLabel = new PaintBackgroundPanel[3];

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public DicesPanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0, 0, 0, 0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0};

        gbl.columnWeights = new double[]{0.03,0.17, 0.06, 0.17, 0.06, 0.17, 0.25, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.91999,0.08, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        diceLabel[0] = new PaintBackgroundPanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        diceLabel[0].setName(DiceColor.BLACK.name());
        diceLabel[0].setOpaque(false);
//        diceLabel[0].setBackground(Color.black);
        pane.add(diceLabel[0], gbc);

        diceLabel[1] = new PaintBackgroundPanel();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        diceLabel[1].setName(DiceColor.WHITE.name());
        diceLabel[1].setOpaque(false);
//        diceLabel[1].setBackground(Color.white);
        pane.add(diceLabel[1], gbc);

        diceLabel[2] = new PaintBackgroundPanel();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        diceLabel[2].setName(DiceColor.ORANGE.name());
        diceLabel[2].setOpaque(false);
//        diceLabel[2].setBackground(Color.orange);
        pane.add(diceLabel[2], gbc);
    }



    public void printDice(List<StateViewFamilyMember> stateViewFamilyMemberList){
        for(int i=0;i<3;i++){
            for (StateViewFamilyMember stateViewFamilyMember : stateViewFamilyMemberList
                    ) {
                if(stateViewFamilyMember.getDiceColor().name().equals(diceLabel[i].getName())){
                    diceLabel[i].setOpaque(false);
                    diceLabel[i].imageToReprint("/dices/dice_"+diceLabel[i].getName()+"_"+stateViewFamilyMember.getDiceValue()+".png");
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
