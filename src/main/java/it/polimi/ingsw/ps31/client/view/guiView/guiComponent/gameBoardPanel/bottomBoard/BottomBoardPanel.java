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
    private MarketActionSpacePanel marketActionSpacePanel;
    private ActionSpaceBoardButton[] actionSpaceBoardButtons = new ActionSpaceBoardButton[4];
    private DicesPanel dicesPanel;


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

        //SmallActionSpaceProduction
        actionSpaceBoardButtons[2]  = new ActionSpaceBoardButton();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        actionSpaceBoardButtons[2].setName(valueOf(4));
        actionSpaceBoardButtons[2].setPreferredSize(new Dimension(10,10));
//        actionSpaceBoardButtons[0].setOpaque(false);
        actionSpaceBoardButtons[2].setContentAreaFilled(false);
        //actionSpaceBoardButtons[0].setBackground(Color.green);
        actionSpaceBoardButtons[2].addActionListener(this);
        pane.add(actionSpaceBoardButtons[2],gbc);

        //SmallActionSpaceHarvest
        actionSpaceBoardButtons[0] = new ActionSpaceBoardButton();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        actionSpaceBoardButtons[0].setName(valueOf(2));
        actionSpaceBoardButtons[0].setContentAreaFilled(false);
        actionSpaceBoardButtons[0].setPreferredSize(new Dimension(10,10));
//        actionSpaceBoardButtons[1].setOpaque(false);
        //actionSpaceBoardButtons[1].setBackground(Color.green);
        actionSpaceBoardButtons[0].addActionListener(this);
        pane.add(actionSpaceBoardButtons[0],gbc);

        //bigActionSpacePanelProduction
        actionSpaceBoardButtons[3] = new ActionSpaceBoardButton();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        actionSpaceBoardButtons[3].setName(valueOf(5));
        actionSpaceBoardButtons[3].setContentAreaFilled(false);
        actionSpaceBoardButtons[3].setPreferredSize(new Dimension(10,10));
//        actionSpaceBoardButtons[2].setOpaque(false);
        //actionSpaceBoardButtons[2].setBackground(Color.RED);
        actionSpaceBoardButtons[3].addActionListener(this);
        pane.add(actionSpaceBoardButtons[3],gbc);

        //bigActionSpacePanelHarvest
        actionSpaceBoardButtons[1] = new ActionSpaceBoardButton();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        actionSpaceBoardButtons[1].setName(valueOf(3));
        actionSpaceBoardButtons[1].setContentAreaFilled(false);
        actionSpaceBoardButtons[1].setPreferredSize(new Dimension(10,10));
        actionSpaceBoardButtons[1].setOpaque(false);
        //actionSpaceBoardButtons[3].setBackground(Color.RED);
        actionSpaceBoardButtons[1].addActionListener(this);
        pane.add(actionSpaceBoardButtons[1],gbc);

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

        dicesPanel = new DicesPanel();
        gbc.gridx = 5;
        gbc.gridy = 4;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        dicesPanel.setPreferredSize(new Dimension(10,10));
        dicesPanel.setOpaque(false);
        //dicesPanel.setBackground(Color.black);
        dicesPanel.attach(this);
        pane.add(dicesPanel,gbc);

    }

    public ActionSpaceBoardButton[] getActionSpaceBoardButtons() {
        return actionSpaceBoardButtons;
    }

    public MarketActionSpacePanel getMarketActionSpacePanel() {
        return marketActionSpacePanel;
    }

    public DicesPanel getDicesPanel() {
        return dicesPanel;
    }


    public void disableBigActionSpace(){
        actionSpaceBoardButtons[1].setEnabled(false);
        actionSpaceBoardButtons[1].imageToReprint("/coveringtile_3_back_.png");
        actionSpaceBoardButtons[3].setEnabled(false);
        actionSpaceBoardButtons[3].imageToReprint("/coveringtile_2_back_.png");

    }


    public void changeButtonBoardState(boolean state){
        actionSpaceBoardButtons[0].setEnabled(state);
        actionSpaceBoardButtons[1].setEnabled(state);
        actionSpaceBoardButtons[2].setEnabled(state);
        actionSpaceBoardButtons[3].setEnabled(state);

        for (ActionSpaceBoardButton button: marketActionSpacePanel.getMarketActionSpace()
             ) {
            button.setEnabled(state);
        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }
}
