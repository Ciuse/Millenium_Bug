package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 07/07/2017.
 */
public class FamilyMemberButton extends JButton implements ActionListener {
    private ActionListener listener;
    private JLabel familyMemberLabel;
    private GuiView guiView;


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public FamilyMemberButton(GuiView guiView) {
        this.guiView=guiView;
        addComponentsToButton(this);
    }

    public void addComponentsToButton(Container pane){


        GridBagLayout gblButton = new GridBagLayout();
        gblButton.columnWidths = new int[]{0, 0, 0,0};
        gblButton.rowHeights = new int[]{0, 0,0,0};
        gblButton.columnWeights = new double[]{0.15,0.70,0.15, Double.MIN_VALUE};
        gblButton.rowWeights = new double[]{0.08,0.84,0.08, Double.MIN_VALUE};
        pane.setLayout(gblButton);

        GridBagConstraints gbcButton = new GridBagConstraints();

        familyMemberLabel = new JLabel();
        gbcButton.gridx = 1;
        gbcButton.gridy = 1;
        gbcButton.gridheight = 1;
        gbcButton.gridwidth = 1;
        gbcButton.fill = GridBagConstraints.BOTH;
        pane.add(familyMemberLabel, gbcButton);

    }

    public JLabel getFamilyMemberLabel() {
        return familyMemberLabel;
    }





    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
