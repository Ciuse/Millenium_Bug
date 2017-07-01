package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 30/06/2017.
 */
public class ExcommunicationPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private ButtonCard buttonExcommunication1;
    private ButtonCard buttonExcommunication2;
    private ButtonCard buttonExcommunication3;


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public ExcommunicationPanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0};
        gbl.rowHeights = new int[]{0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.333333,0.333333,0.333333, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.15,0.66,0.1899, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonExcommunication1 = new ButtonCard();
        buttonExcommunication1.setName("ExcommunicationFirstPeriod");
        buttonExcommunication1.imageToLoad("/excommunications/excomm_back_1.png");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //buttonExcommunication1.setBackground(Color.green);
        buttonExcommunication1.addActionListener(this);
        //buttonExcommunication1.setOpaque(false);
        pane.add(buttonExcommunication1,gbc);



        buttonExcommunication2 = new ButtonCard();
        buttonExcommunication2.setName("ExcommunicationSecondPeriod");
        buttonExcommunication2.imageToLoad("/excommunications/excomm_back_2.png");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //buttonExcommunication2.setBackground(Color.red);
        buttonExcommunication2.addActionListener(this);
        //buttonExcommunication2.setOpaque(false);
        pane.add(buttonExcommunication2,gbc);

        buttonExcommunication3 = new ButtonCard();
        buttonExcommunication3.setName("ExcommunicationThirdPeriod");
        buttonExcommunication3.imageToLoad("/excommunications/excomm_back_3.png");
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //buttonExcommunication3.setBackground(Color.yellow);
        buttonExcommunication3.addActionListener(this);
        //buttonExcommunication3.setOpaque(false);
        pane.add(buttonExcommunication3,gbc);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
