package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 26/06/2017.
 */
public class UtilityPanel extends JPanel implements ActionListener {
    private GuiView guiView;
    private QuestionsToPlayerPanel questionsToPlayerPanel;
    private StateGameOtherPlayersPanel stateGameOtherPlayersPanel;

    public UtilityPanel(GuiView guiView) {
        this.guiView=guiView;
        addComponentsToPane(this);

    }


    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0};
        gbl.rowHeights = new int[]{0, 0,0};

        gbl.columnWeights = new double[]{0.999999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.70, 0.30, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        questionsToPlayerPanel = new QuestionsToPlayerPanel(this.guiView);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        questionsToPlayerPanel.setOpaque(false);
        //questionsToPlayerPanel.setBackground(Color.green);
        pane.add(questionsToPlayerPanel,gbc);

        stateGameOtherPlayersPanel = new StateGameOtherPlayersPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        stateGameOtherPlayersPanel.setOpaque(false);
        //stateGameOtherPlayersPanel.setBackground(Color.red);
        pane.add(stateGameOtherPlayersPanel,gbc);
    }

    public QuestionsToPlayerPanel getQuestionsToPlayerPanel() {
        return questionsToPlayerPanel;
    }

    public StateGameOtherPlayersPanel getStateGameOtherPlayersPanel() {
        return stateGameOtherPlayersPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
