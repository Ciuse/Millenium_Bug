package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.stateModel.StateFamilyMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static it.polimi.ingsw.ps31.model.constants.PlayerColor.*;

/**
 * Created by giulia on 29/06/2017.
 * classe che mi rappresenta l'action space delle torri dove possono essere posizionati i family member
 * @see PaintBackgroundPanel
 */
public class SingleSmallActionSpacePanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private PaintBackgroundPanel[] familyMemberPanel = new PaintBackgroundPanel[3];

    /* Constructor */
    public SingleSmallActionSpacePanel() {
        addComponentsToPane(this);
    }


    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }


    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     */
    public void addComponentsToPane(Container pane){
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0};

        gbl.columnWeights = new double[]{0.25,0.16,0.16,0.16,0.24999,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.295,0.255,0.4299,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();


        familyMemberPanel[0] = new PaintBackgroundPanel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMemberPanel[0].setOpaque(false);
        familyMemberPanel[0].setPreferredSize(new Dimension(10, 10));
        pane.add(familyMemberPanel[0], gbc);


        familyMemberPanel[1] = new PaintBackgroundPanel();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMemberPanel[1].setOpaque(false);
        familyMemberPanel[1].setPreferredSize(new Dimension(10, 10));
        pane.add(familyMemberPanel[1], gbc);

        familyMemberPanel[2] = new PaintBackgroundPanel();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMemberPanel[2].setOpaque(false);
        familyMemberPanel[2].setPreferredSize(new Dimension(10, 10));
        pane.add(familyMemberPanel[2], gbc);
        }

    /**
     * Metodo che mi permette di stampare i family member negli action space delle torri
     */
    public void printFamilyMemberOnTopBoard(List<StateFamilyMember> stateFamilyMemberList) {
        if (stateFamilyMemberList.size() != 0) {
            int i = 0;
                for (StateFamilyMember familyMember : stateFamilyMemberList
                        ) {
                    if(familyMember.getDiceColor().equals(DiceColor.NEUTRAL)){
                        this.familyMemberPanel[i].imageToReprint("/player/" +familyMember.getPlayerColor().name()+ "_Neutral.png");;
                    }else {
                        this.familyMemberPanel[i].setOpaque(true);
                        this.familyMemberPanel[i].setBackground(getFamilyMemberColor(familyMember.getPlayerColor()));
                    }
                        i++;
                }
            }
        }

    /**
     * metodo che mi converte un player color in un color
     */
    public Color getFamilyMemberColor(PlayerColor playerColor){
        if(playerColor.equals(RED)){
            return Color.RED;
        }if(playerColor.equals(GREEN)){
            return Color.GREEN;
        }if(playerColor.equals(BLUE)){
            return Color.BLUE;
        }if(playerColor.equals(YELLOW)){
            return Color.YELLOW;
        } else return null;
    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
