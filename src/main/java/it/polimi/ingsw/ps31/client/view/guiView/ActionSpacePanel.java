package it.polimi.ingsw.ps31.client.view.guiView;

import it.polimi.ingsw.ps31.model.constants.PlayerColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 29/06/2017.
 */
public class ActionSpacePanel extends JPanel  {
    private ActionListener listener;
    private JPanel[] familyMemberPanel = new JPanel[2];


    public ActionSpacePanel() {
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0};

        gbl.columnWeights = new double[]{0.25,0.25,0.25,0.25,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.27,0.28,0.4199,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

       for(int i=0;i<2;i++){
            familyMemberPanel[i] = new JPanel();
            gbc.gridx = i+1;
            gbc.gridy = 1;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            //familyMemberPanel[i].setBackground(Color.RED);
            //jPanel1.setOpaque(false);
            familyMemberPanel[i].setPreferredSize(new Dimension(10, 10));

            pane.add(familyMemberPanel[i], gbc);
        }


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
