package it.polimi.ingsw.ps31.client.view.guiView;

import it.polimi.ingsw.ps31.*;
import it.polimi.ingsw.ps31.Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by giulia on 28/06/2017.
 */
public class DevelopmentCardOnTowerPanel extends JPanel implements ActionListener{
    private ActionListener listener;
    private String numberOfActionSpace;
    ButtonCard jButtonPanel = new ButtonCard();
    ActionSpacePanel actionSpacePanel = new ActionSpacePanel();


    public DevelopmentCardOnTowerPanel(String numberOfActionSpace) {
        this.numberOfActionSpace = numberOfActionSpace;
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(){
        Container pane=this;
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0};

        gbl.columnWeights = new double[]{0.52,0.48,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.30,0.454,0.156,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        jButtonPanel.setName(numberOfActionSpace);
        jButtonPanel.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(jButtonPanel,gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //actionSpacePanel.setBackground(Color.green);
        actionSpacePanel.setOpaque(false);
        pane.add(actionSpacePanel,gbc);
    }

    public ActionSpacePanel getActionSpacePanel() {
        return actionSpacePanel;
    }

    public String getNumberOfActionSpace() {
        return numberOfActionSpace;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }


    public ButtonCard getjButtonPanel() {
        return jButtonPanel;
    }
}
