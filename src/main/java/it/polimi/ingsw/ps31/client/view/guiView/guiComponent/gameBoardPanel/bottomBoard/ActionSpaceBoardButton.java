package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.stateModel.StateFamilyMember;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by giulia on 01/07/2017.
 */
public class ActionSpaceBoardButton extends ButtonCard implements ActionListener {
    private ActionListener listener;
    private PaintBackgroundPanel[] familyMember = new PaintBackgroundPanel[6];

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public ActionSpaceBoardButton() {
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
        familyMember[0].setOpaque(false);
        familyMember[0].setPreferredSize(new Dimension(10,10));
        //familyMember[0].setBackground(Color.BLUE);
        pane.add(familyMember[0], gbc);

        familyMember[1] = new PaintBackgroundPanel();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[1].setOpaque(false);
        familyMember[1].setPreferredSize(new Dimension(10,10));
        //familyMember[1].setBackground(Color.green);
        pane.add(familyMember[1], gbc);

        familyMember[2] = new PaintBackgroundPanel();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[2].setOpaque(false);
        familyMember[2].setPreferredSize(new Dimension(10,10));
        //familyMember[2].setBackground(Color.pink);
        pane.add(familyMember[2], gbc);

        familyMember[3] = new PaintBackgroundPanel();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[3].setOpaque(false);
        familyMember[3].setPreferredSize(new Dimension(10,10));
        //familyMember[3].setBackground(Color.black);
        pane.add(familyMember[3], gbc);

        familyMember[4] = new PaintBackgroundPanel();
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[4].setOpaque(false);
        familyMember[4].setPreferredSize(new Dimension(10,10));
        //familyMember[4].setBackground(Color.yellow);
        pane.add(familyMember[4], gbc);

        familyMember[5] = new PaintBackgroundPanel();
        gbc.gridx = 6;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        familyMember[5].setOpaque(false);
        familyMember[5].setPreferredSize(new Dimension(10,10));
        //familyMember[5].setBackground(Color.red);
        pane.add(familyMember[5], gbc);

        }

 public void printFamilyMemberOnBottomBoard(List<StateFamilyMember> stateFamilyMemberList) {
     if (stateFamilyMemberList.size() != 0) {
         int i = 0;
             for (StateFamilyMember familyMember : stateFamilyMemberList
                     ) {
                 if(familyMember.getDiceColor().equals(DiceColor.NEUTRAL)){
                     this.familyMember[i].imageToReprint("/player/" +familyMember.getPlayerColor().name()+ "_Neutral.png");
                 }else  {
                     this.familyMember[i].setOpaque(true);
                     this.familyMember[i].setBackground(getFamilyMemberColor(familyMember.getPlayerColor()));
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


    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }
}
