package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 26/06/2017.
 */
public class BottomBoardPanel extends JPanel  {
    private ActionListener listener;
    private SmallActionSpace smallActionSpace;
    private BigActionSpacePanel bigActionSpacePanel;
    private MarketActionSpacePanel marketActionSpacePanel;
    private DicePanel dicePanel;


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public BottomBoardPanel() {
        addComponentsToPane(this);

    }
    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.15, 0.35, 0.499, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.6799, 0.08, 0.24, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        smallActionSpace = new SmallActionSpace();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        smallActionSpace.setOpaque(false);
        //smallActionSpace.setBackground(Color.green);
        pane.add(smallActionSpace,gbc);

        bigActionSpacePanel = new BigActionSpacePanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        bigActionSpacePanel.setOpaque(false);
        //bigActionSpacePanel.setBackground(Color.RED);
        pane.add(bigActionSpacePanel,gbc);

        marketActionSpacePanel = new MarketActionSpacePanel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        marketActionSpacePanel.setOpaque(false);
        //marketActionSpacePanel.setBackground(Color.pink);
        pane.add(marketActionSpacePanel,gbc);

        dicePanel = new DicePanel();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        dicePanel.setOpaque(false);
        //dicePanel.setBackground(Color.black);
        pane.add(dicePanel,gbc);

    }

}
