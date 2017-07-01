package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 30/06/2017.
 */
public class BlueCardPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private DevelopmentCardOnPersonalBoardPanel developmentCardOnPersonalBoardPanel;

    public BlueCardPanel() {
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0};
        gbl.rowHeights = new int[]{0,0};

        gbl.columnWeights = new double[]{0.9999,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.99999,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        developmentCardOnPersonalBoardPanel = new DevelopmentCardOnPersonalBoardPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(developmentCardOnPersonalBoardPanel,gbc);
        developmentCardOnPersonalBoardPanel.attach(this);
    }


        @Override
    public void actionPerformed(ActionEvent e) {

    }
}
