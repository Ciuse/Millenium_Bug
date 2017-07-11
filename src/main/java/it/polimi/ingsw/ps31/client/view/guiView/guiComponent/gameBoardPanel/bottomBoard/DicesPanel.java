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
 * * classe che rappresenta il pannello contenente a sua volta tre pannelli per i dadi a cui viene caricato uno sfondo
 * diverso a seconda del numero uscito con il tiro del dado
 *  @see ActionListener
 *   @see PaintBackgroundPanel
 *
 */
public class DicesPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private PaintBackgroundPanel[] dice = new PaintBackgroundPanel[3];

    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }

    /* Constructor */
    public DicesPanel() {
        addComponentsToPane(this);
    }


    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio dei 3 dadi
     */
    public void addComponentsToPane(Container pane) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0, 0, 0, 0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0};

        gbl.columnWeights = new double[]{0.03,0.17, 0.06, 0.17, 0.06, 0.17, 0.25, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.91999,0.08, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        dice[0] = new PaintBackgroundPanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        dice[0].setName(DiceColor.BLACK.name());
        dice[0].setOpaque(false);
        pane.add(dice[0], gbc);

        dice[1] = new PaintBackgroundPanel();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        dice[1].setName(DiceColor.WHITE.name());
        dice[1].setOpaque(false);
        pane.add(dice[1], gbc);

        dice[2] = new PaintBackgroundPanel();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        dice[2].setName(DiceColor.ORANGE.name());
        dice[2].setOpaque(false);
        pane.add(dice[2], gbc);
    }


    /**
     * Metodo che mi permette di stampare il dado ogni volta che il suo valore cambia cioè ogni volta che viene tirato
     */
    public void printDice(List<StateViewFamilyMember> stateViewFamilyMemberList){
        for(int i=0;i<3;i++){
            for (StateViewFamilyMember stateViewFamilyMember : stateViewFamilyMemberList
                    ) {
                if(stateViewFamilyMember.getDiceColor().name().equals(dice[i].getName())){
                    dice[i].setOpaque(false);
                    dice[i].imageToReprint("/dices/dice_"+ dice[i].getName()+"_"+stateViewFamilyMember.getDiceValue()+".png");
                }
            }
        }
    }

    /**
     * Metodo che mi permette di sostituire un Color con un diceColor
     */
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
