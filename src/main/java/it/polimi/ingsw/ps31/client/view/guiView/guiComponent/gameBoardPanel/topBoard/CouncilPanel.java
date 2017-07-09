package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard;


import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard.SingleBigActionSpace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 26/06/2017.
 */
public class CouncilPanel extends JPanel implements ActionListener{
    private ActionListener listener;
    private ExcommunicationPanel excommunicationPanel;
    private SingleBigActionSpace actionSpaceCouncilButton;


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

        gbl.columnWeights = new double[]{0.17,0.26,0.07,0.29,0.2099, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.10, 0.22, 0.6799, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        excommunicationPanel = new ExcommunicationPanel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        excommunicationPanel.setPreferredSize(new Dimension(10,10));
        excommunicationPanel.setOpaque(false);
        //excommunicationPanel.setBackground(Color.RED);
        pane.add(excommunicationPanel,gbc);

        actionSpaceCouncilButton = new SingleBigActionSpace();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        actionSpaceCouncilButton.setName(valueOf(1));
        actionSpaceCouncilButton.setPreferredSize(new Dimension(10,10));
        actionSpaceCouncilButton.setContentAreaFilled(false);
        actionSpaceCouncilButton.setOpaque(false);
        actionSpaceCouncilButton.addActionListener(this);
        //actionSpaceCouncilButton.setBackground(Color.green);
        pane.add(actionSpaceCouncilButton,gbc);

    }

    public ExcommunicationPanel getExcommunicationPanel() {
        return excommunicationPanel;
    }

    public SingleBigActionSpace getActionSpaceCouncilButton() {
        return actionSpaceCouncilButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }
}
