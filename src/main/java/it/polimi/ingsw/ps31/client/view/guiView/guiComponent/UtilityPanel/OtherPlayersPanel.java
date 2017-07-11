package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalCardBox;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by giulia on 10/07/2017.
 */
public class OtherPlayersPanel extends JPanel implements ActionListener {
    private JFrame father = null;
    private GuiView guiView;
    private SingleOtherPlayersPanel[] singleOtherPlayersPanel = new SingleOtherPlayersPanel[3];
    private GridBagLayout gbl;

    public OtherPlayersPanel(GuiView guiView) {
        this.guiView = guiView;
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0};

        gbl.columnWeights = new double[]{0.32, 0.02, 0.32, 0.02, 0.32, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.9999, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();

        int i = 0;
        for (StateViewPlayer player : guiView.getStateViewPlayerList()
                ) {
            if (player.getPlayerId() != guiView.getMyStateViewPlayer().getPlayerId()) {
                singleOtherPlayersPanel[i] = new SingleOtherPlayersPanel(this.guiView, player.getPlayerId());
                gbc.gridx = i * 2;
                gbc.gridy = 0;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                singleOtherPlayersPanel[i].setPreferredSize(new Dimension(10, 10));
                gbc.fill = GridBagConstraints.BOTH;
                pane.add(singleOtherPlayersPanel[i], gbc);
                i++;
            }
        }



    }

    public void fillOtherPlayers(StateViewPersonalBoard stateViewPersonalBoard){
        int i = 0;
        for (StateViewPlayer player : guiView.getStateViewPlayerList()
                ) {
            if (player.getPlayerId() != guiView.getMyStateViewPlayer().getPlayerId()) {
                singleOtherPlayersPanel[i].getjPersonalBoardPanel().getCardYellow().setStateViewPersonalCardBoxList(stateViewPersonalBoard.getStateViewPersonalCardBoxListYellow());
                singleOtherPlayersPanel[i].getjPersonalBoardPanel().getCardYellow().fillDevelopmentCardPanel();
                singleOtherPlayersPanel[i].getjPersonalBoardPanel().getCardGreen().setStateViewPersonalCardBoxList(stateViewPersonalBoard.getStateViewPersonalCardBoxListGreen());
                singleOtherPlayersPanel[i].getjPersonalBoardPanel().getCardGreen().fillDevelopmentCardPanel();
                singleOtherPlayersPanel[i].getExtraCardPanel().getPurpleCardPanel().setStateViewPersonalCardBoxList(stateViewPersonalBoard.getStateViewPersonalCardBoxListPurple());
                singleOtherPlayersPanel[i].getExtraCardPanel().getPurpleCardPanel().fillDevelopmentCardPanel();
                singleOtherPlayersPanel[i].getExtraCardPanel().getBlueCardPanel().setStateViewPersonalCardBoxList(stateViewPersonalBoard.getStateViewPersonalCardBoxListBlue());
                singleOtherPlayersPanel[i].getExtraCardPanel().getBlueCardPanel().fillDevelopmentCardPanel();
                singleOtherPlayersPanel[i].getjLeaderCardPanel().getLeaderCardsOpenedPanel().setStateViewLeaderCardList(player.getStateViewLeaderCardList());
                singleOtherPlayersPanel[i].getjLeaderCardPanel().getLeaderCardsOpenedPanel().fillLeaderPanel();
                singleOtherPlayersPanel[i].getjPersonalBonusTilesPanel().printTiles(player.getStateViewPersonalBonusTiles());
                singleOtherPlayersPanel[i].getjPersonalBoardPanel().getPlayerResourcesPanel().setStringPhysicalResources(player.getPlayerResources());
                singleOtherPlayersPanel[i].getjPersonalBoardPanel().getPlayerResourcesPanel().setStringPointResources(player.getPlayerResources());


            }i++;
        }
    }



    public void setFather(JFrame father) {
        this.father = father;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
