package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.ButtonCard;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewLeaderCard;
import it.polimi.ingsw.ps31.model.stateModel.StateLeaderCard;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 30/06/2017.
 */
public class PersonalLeaderCardsPanel extends JPanel {
    private ActionListener listener;
    private PaintBackgroundPanel[] leader = new PaintBackgroundPanel[4];

    private List<StateViewLeaderCard> stateViewLeaderCardList;


    public PersonalLeaderCardsPanel() {
        addComponentsToPane(this);
    }

    public ActionListener getListener() {
        return listener;
    }

    public void attach(ActionListener listener) {
        this.listener = listener;
    }


    public void addComponentsToPane(Container pane) {

        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0, 0,0,0};

        gbl.columnWeights = new double[]{0.01, 0.2375, 0.01, 0.2375, 0.01, 0.2375, 0.01, 0.2375, 0.01, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.01,0.98,0.01, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();
        for (int i = 0; i < 4; i++) {
            leader[i] = new PaintBackgroundPanel();
            gbc.gridx = (2 * i) + 1;;
            gbc.gridy = 1;
            gbc.gridheight = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            pane.add(leader[i], gbc);
        }
    }


    public void fillLeaderPanel() {
        if (!stateViewLeaderCardList.isEmpty()) {
            int i=0;
            for (StateViewLeaderCard leaderCard : stateViewLeaderCardList
                    ) {
                leader[i].imageToReprint("/leaderCard/leaders_f_c_" + valueOf(leaderCard.getLeaderId()) + ".jpg");
                i++;
            }
        }
    }
    public void setStateViewLeaderCardList(List<StateViewLeaderCard> stateViewLeaderCardList) {
        this.stateViewLeaderCardList = stateViewLeaderCardList;
    }
}