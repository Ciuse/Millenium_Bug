package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.stateModel.StateFamilyMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by giulia on 29/06/2017.
 */
public class SingleSmallActionSpacePanel extends JPanel  {
    private ActionListener listener;
    private PaintBackgroundPanel[] familyMemberPanel = new PaintBackgroundPanel[3];


    public SingleSmallActionSpacePanel() {
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
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
        //familyMemberPanel[0].setBackground(Color.BLUE);
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


    public Color getFamilyMemberColor(PlayerColor playerColor){
        if(playerColor==playerColor.RED){
            return Color.RED;
        }if(playerColor==playerColor.GREEN){
            return Color.GREEN;
        }if(playerColor==playerColor.BLUE){
            return Color.BLUE;
        }if(playerColor==playerColor.YELLOW){
            return Color.YELLOW;
        } else return null;
    }

    public JPanel[] getFamilyMemberPanel() {
        return familyMemberPanel;
    }

    public void printFamilyMember(int elementNumber, PlayerColor playerColor){
        if(playerColor.equals(PlayerColor.BLUE)){
            familyMemberPanel[elementNumber].setBackground(Color.BLUE);
        }if(playerColor.equals(PlayerColor.YELLOW)){
            familyMemberPanel[elementNumber].setBackground(Color.YELLOW);
        }if(playerColor.equals(PlayerColor.RED)){
            familyMemberPanel[elementNumber].setBackground(Color.RED);
        }if(playerColor.equals(PlayerColor.GREEN)){
            familyMemberPanel[elementNumber].setBackground(Color.GREEN);
        }



    }
}
