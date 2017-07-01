package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 26/06/2017.
 */
public class CouncilPanel extends JPanel implements ActionListener{
    private ActionListener listener;
    private ExcommunicationPanel excommunicationPanel;
    private SingleBigActionSpace actionSpaceCouncilPanel;


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public CouncilPanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0,0,0};
        gbl.rowHeights = new int[]{0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.222,0.11,0.088,0.3508,0.2299, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.24, 0.11, 0.65, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        excommunicationPanel = new ExcommunicationPanel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        excommunicationPanel.setOpaque(false);
        //excommunicationPanel.setBackground(Color.RED);
        pane.add(excommunicationPanel,gbc);

        actionSpaceCouncilPanel = new SingleBigActionSpace();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //actionSpaceCouncilPanel.setOpaque(false);
        actionSpaceCouncilPanel.setBackground(Color.green);
        pane.add(actionSpaceCouncilPanel,gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
