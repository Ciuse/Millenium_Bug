package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 26/06/2017.
 */
public class BottomBoardPanel extends JPanel implements ActionListener  {
    private ActionListener listener;
    private SingleBigActionSpace smallActionSpaceProduction;
    private SingleBigActionSpace smallActionSpaceHarvest;
    private SingleBigActionSpace bigActionSpacePanelProduction;
    private SingleBigActionSpace bigActionSpacePanelHarvest;
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
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0,0,0};
        gbl.rowHeights = new int[]{0, 0, 0, 0,0,0,0};

        gbl.columnWeights = new double[]{0.02,0.06,0.05,0.26,0.17, 0.45, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.28, 0.25,0.18,0.04, 0.21,0.04, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        smallActionSpaceProduction = new SingleBigActionSpace();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        smallActionSpaceProduction.setName(valueOf(4));
        smallActionSpaceProduction.setPreferredSize(new Dimension(10,10));
//        smallActionSpaceProduction.setOpaque(false);
        smallActionSpaceProduction.setContentAreaFilled(false);
        //smallActionSpaceProduction.setBackground(Color.green);
        smallActionSpaceProduction.attach(this);
        pane.add(smallActionSpaceProduction,gbc);

        smallActionSpaceHarvest = new SingleBigActionSpace();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        smallActionSpaceHarvest.setName(valueOf(2));
        smallActionSpaceHarvest.setContentAreaFilled(false);
        smallActionSpaceHarvest.setPreferredSize(new Dimension(10,10));
//        smallActionSpaceHarvest.setOpaque(false);
        //smallActionSpaceHarvest.setBackground(Color.green);
        smallActionSpaceHarvest.attach(this);
        pane.add(smallActionSpaceHarvest,gbc);


        bigActionSpacePanelProduction = new SingleBigActionSpace();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        bigActionSpacePanelProduction.setName(valueOf(5));
        bigActionSpacePanelProduction.setContentAreaFilled(false);
        bigActionSpacePanelProduction.setPreferredSize(new Dimension(10,10));
//        bigActionSpacePanelProduction.setOpaque(false);
        //bigActionSpacePanelProduction.setBackground(Color.RED);
        bigActionSpacePanelProduction.attach(this);
        pane.add(bigActionSpacePanelProduction,gbc);

        bigActionSpacePanelHarvest = new SingleBigActionSpace();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        bigActionSpacePanelHarvest.setName(valueOf(3));
        bigActionSpacePanelHarvest.setContentAreaFilled(false);
        bigActionSpacePanelHarvest.setPreferredSize(new Dimension(10,10));
        bigActionSpacePanelHarvest.setOpaque(false);
        //bigActionSpacePanelHarvest.setBackground(Color.RED);
        bigActionSpacePanelHarvest.attach(this);
        pane.add(bigActionSpacePanelHarvest,gbc);

        marketActionSpacePanel = new MarketActionSpacePanel();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        marketActionSpacePanel.setPreferredSize(new Dimension(10,10));
        marketActionSpacePanel.setOpaque(false);
        //marketActionSpacePanel.setBackground(Color.pink);
        marketActionSpacePanel.attach(this);
        pane.add(marketActionSpacePanel,gbc);

        dicePanel = new DicePanel();
        gbc.gridx = 5;
        gbc.gridy = 4;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        dicePanel.setPreferredSize(new Dimension(10,10));
        //dicePanel.setOpaque(false);
        dicePanel.setBackground(Color.black);
        dicePanel.attach(this);
        pane.add(dicePanel,gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }
}
