package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 30/06/2017.
 */
public class PersonalLeaderCardsPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    DevelopmentCardOnPersonalBoardPanel developmentCardOnPersonalBoardPanel;



    public PersonalLeaderCardsPanel() {
        addComponentsToPane(this);
    }

    public ActionListener getListener() {
        return listener;
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }


        public void addComponentsToPane(Container pane){

            GridBagLayout gbl = new GridBagLayout();
            gbl.columnWidths = new int[]{0, 0, 0, 0, 0,};
            gbl.rowHeights = new int[]{0, 0,};

            gbl.columnWeights = new double[]{0.25, 0.25, 0.25, 0.25, Double.MIN_VALUE};
            gbl.rowWeights = new double[]{0.99999, Double.MIN_VALUE};
            pane.setLayout(gbl);

            GridBagConstraints gbc = new GridBagConstraints();

            ButtonCard jButtonPanel1 = new ButtonCard();
            jButtonPanel1.setName("1");
            jButtonPanel1.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            pane.add(jButtonPanel1, gbc);

            ButtonCard jButtonPanel2 = new ButtonCard();
            jButtonPanel2.setName("2");
            jButtonPanel2.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            pane.add(jButtonPanel2, gbc);

            ButtonCard jButtonPanel3 = new ButtonCard();
            jButtonPanel3.setName("3");
            jButtonPanel3.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            pane.add(jButtonPanel3, gbc);

            ButtonCard jButtonPanel4 = new ButtonCard();
            jButtonPanel4.setName("4");
            jButtonPanel4.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            pane.add(jButtonPanel4, gbc);


        }

        @Override
    public void actionPerformed(ActionEvent e) {

    }
}
