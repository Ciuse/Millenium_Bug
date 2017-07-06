package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewLeaderCard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 04/07/2017.
 */
public class ChoosenButtonPanel extends PaintBackgroundPanel implements ActionListener {
    private ActionListener listener;
    private ButtonCard button1 = new ButtonCard("Family member in board");
    private ButtonCard button2 = new ButtonCard("Family member in tower");;
    private ButtonCard button3 = new ButtonCard("Attiva leader");;
    private ButtonCard button4 = new ButtonCard("Scarta leader");;
    private ButtonCard button5 = new ButtonCard("FINE TURNO");;
    private ButtonCard[] buttonsAction = new ButtonCard[5];
    private GuiView guiView;


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public ChoosenButtonPanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0, 0,0,0,0,0,0,0};

        gbl.columnWeights = new double[]{0.08,0.28,0.02,0.28,0.02,0.28,0.04, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.08,0.205,0.205,0.02,0.205,0.205,0.08, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();



        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        button1.setName("Place FM Board");
        button1.setEnabled(false);
        button1.setPreferredSize(new Dimension(10, 10));
        pane.add(button1, gbc);
        button1.addActionListener(this);
        buttonsAction[0] = button1;


        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        button2.setEnabled(false);
        gbc.fill = GridBagConstraints.BOTH;
        button2.setName("Place FM in Tower");
        button2.setPreferredSize(new Dimension(10, 10));
        pane.add(button2, gbc);
        button2.addActionListener(this);
        buttonsAction[1] = button2;



        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        button3.setEnabled(false);
        button3.setName("Active Leader");
        gbc.fill = GridBagConstraints.BOTH;
        button3.setPreferredSize(new Dimension(10, 10));
        pane.add(button3, gbc);
        button3.addActionListener(this);
        buttonsAction[2] = button3;



        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        button4.setEnabled(false);
        button4.setName("DiscardLeader");
        gbc.fill = GridBagConstraints.BOTH;
        button4.setPreferredSize(new Dimension(10, 10));
        pane.add(button4, gbc);
        button4.addActionListener(this);
        buttonsAction[3] = button4;



        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        button5.setEnabled(false);
        gbc.fill = GridBagConstraints.BOTH;
        button5.setName("End Turn");
        button5.setPreferredSize(new Dimension(10, 10));
        pane.add(button5, gbc);
        button5.addActionListener(this);
        buttonsAction[4] = button5;



    }

    public void setEnabledActions() {
        for(int i = 0; i<5;i++) {
            boolean found = false;
        for (String string : guiView.getMyStateViewPlayer().getStringPlayerAction()
                ) {
            if(string.equals(buttonsAction[i].getName())){
                found=true;
            }
        }if(found){
                buttonsAction[i].setEnabled(true);
            }else buttonsAction[i].setEnabled(false);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
