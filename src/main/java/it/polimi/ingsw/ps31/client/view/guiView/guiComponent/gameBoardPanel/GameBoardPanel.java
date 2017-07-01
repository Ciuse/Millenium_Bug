package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard.BottomBoardPanel;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard.TopBoardPanel;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.trackPanel.*;
import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.messages.messageVC.VCTowerCardSpaceChoice;
import it.polimi.ingsw.ps31.model.constants.CardColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 25/06/2017.
 */
public class GameBoardPanel extends PaintBackgroundPanel implements ActionListener {
    private VictoryPointTrackFirstColumnPanel victoryPointTrackFirstColumnPanel;
    private VictoryPointTrackFirstRowPanel victoryPointTrackFirstRowPanel;
    private TopBoardPanel topBoardPanel;
    private FaithPointTrackPanel faithPointTrackPanel;
    private BottomBoardPanel bottomBoardPanel;
    private VictoryPointTrackSecondRowPanel victoryPointTrackSecondRowPanel;
    private MilitaryTrackPanel militaryTrackPanel;
    private VictoryPointTrackSecondColumnPanel victoryPointTrackSecondColumnPanel;
    private GuiView guiView;

    public void paintComponent(Graphics g) {
        super.imageToLoad("/gameboard1_f_c.png");
        super.paintComponent(g);
    }

    public GameBoardPanel(GuiView guiView) {
        this.guiView = guiView;
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.04, 0.85, 0.08, 0.046, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.04, 0.710, 0.015, 0.202, 0.033, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();


        victoryPointTrackFirstColumnPanel = new VictoryPointTrackFirstColumnPanel();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 4;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        //victoryPointTrackFirstColumnPanel.setBackground(Color.CYAN);
        victoryPointTrackFirstColumnPanel.setOpaque(false);
        victoryPointTrackFirstColumnPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(victoryPointTrackFirstColumnPanel, c);
        victoryPointTrackFirstColumnPanel.attach(this);

        victoryPointTrackFirstRowPanel = new VictoryPointTrackFirstRowPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.BOTH;
        //victoryPointTrackFirstRowPanel.setBackground(Color.BLUE);
        victoryPointTrackFirstRowPanel.setOpaque(false);
        victoryPointTrackFirstRowPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(victoryPointTrackFirstRowPanel, c);
        victoryPointTrackFirstRowPanel.attach(this);

        topBoardPanel = new TopBoardPanel();
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH; //toglierlo edopo
        //topBoardPanel.setBackground(Color.YELLOW);
        topBoardPanel.setOpaque(false);
        topBoardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(topBoardPanel, c);
        topBoardPanel.attach(this);

        faithPointTrackPanel = new FaithPointTrackPanel();
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        //faithPointTrackPanel.setBackground(Color.red);
        faithPointTrackPanel.setOpaque(false);
        faithPointTrackPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(faithPointTrackPanel, c);
        faithPointTrackPanel.attach(this);

        bottomBoardPanel = new BottomBoardPanel();
        bottomBoardPanel.attach(this);
        c.gridx = 1;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        //bottomBoardPanel.setBackground(Color.YELLOW);
        bottomBoardPanel.setOpaque(false);
        bottomBoardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(bottomBoardPanel, c);
        bottomBoardPanel.attach(this);

        victoryPointTrackSecondRowPanel = new VictoryPointTrackSecondRowPanel();
        c.gridx = 1;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.BOTH;
        //victoryPointTrackSecondRowPanel.setBackground(Color.PINK);
        victoryPointTrackSecondRowPanel.setOpaque(false);
        victoryPointTrackSecondRowPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(victoryPointTrackSecondRowPanel, c);
        victoryPointTrackSecondRowPanel.attach(this);

        militaryTrackPanel = new MilitaryTrackPanel();
        c.gridx = 2;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        //militaryTrackPanel.setBackground(Color.black);
        militaryTrackPanel.setOpaque(false);
        militaryTrackPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(militaryTrackPanel, c);
        militaryTrackPanel.attach(this);

        victoryPointTrackSecondColumnPanel = new VictoryPointTrackSecondColumnPanel();
        c.gridx = 3;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;
        //victoryPointTrackSecondColumnPanel.setBackground(Color.RED);
        victoryPointTrackSecondColumnPanel.setOpaque(false);
        victoryPointTrackSecondColumnPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(victoryPointTrackSecondColumnPanel, c);
        victoryPointTrackSecondColumnPanel.attach(this);

    }

    public TopBoardPanel getTopBoardPanel() {
        return topBoardPanel;
    }

    public VictoryPointTrackFirstColumnPanel getVictoryPointTrackFirstColumnPanel() {
        return victoryPointTrackFirstColumnPanel;
    }

    public VictoryPointTrackFirstRowPanel getVictoryPointTrackFirstRowPanel() {
        return victoryPointTrackFirstRowPanel;
    }

    public FaithPointTrackPanel getFaithPointTrackPanel() {
        return faithPointTrackPanel;
    }

    public BottomBoardPanel getBottomBoardPanel() {
        return bottomBoardPanel;
    }

    public VictoryPointTrackSecondRowPanel getVictoryPointTrackSecondRowPanel() {
        return victoryPointTrackSecondRowPanel;
    }

    public MilitaryTrackPanel getMilitaryTrackPanel() {
        return militaryTrackPanel;
    }

    public VictoryPointTrackSecondColumnPanel getVictoryPointTrackSecondColumnPanel() {
        return victoryPointTrackSecondColumnPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComponent buttonClicked = (JComponent) e.getSource();
        int buttonNumber = Integer.parseInt(buttonClicked.getName());
        System.out.println(buttonNumber);
        if (getTopBoardPanel().getTowerPanel().isSendNextClick()) {
            CardColor cardColor=getTopBoardPanel().getTowerPanel().getCardColorFromButtonNumber(buttonNumber);
            int floorNumber=getTopBoardPanel().getTowerPanel().getFloorNumberFromButtonName(buttonNumber);
            guiView.notifyController(new VCTowerCardSpaceChoice(guiView.getViewId(),cardColor,floorNumber));
        }

    }

}