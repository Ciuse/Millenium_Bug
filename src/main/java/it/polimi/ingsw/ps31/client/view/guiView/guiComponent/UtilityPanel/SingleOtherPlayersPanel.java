package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel.*;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 10/07/2017.
 */
public class SingleOtherPlayersPanel extends JPanel implements ActionListener {
    private GuiView guiView;
    private PlayerId numberOfPlayer;
    private PersonalBonusTilesPanel jPersonalBonusTilesPanel;
    private PersonalBoardPanel jPersonalBoardPanel;
    private ExtraCardPanel extraCardPanel;
    private LeaderCardPanel jLeaderCardPanel;


    public SingleOtherPlayersPanel(GuiView guiView, PlayerId numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
        this.guiView = guiView;
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.06, 0.44, 0.25, 0.115, 0.13, 0.00999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.00999, 0.39, 0.41, 0.19, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        jPersonalBonusTilesPanel = new PersonalBonusTilesPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //jPersonalBonusTilesPanel.setBackground(Color.RED);
        //jPersonalBonusTilesPanel.setOpaque(true);
        jPersonalBonusTilesPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(jPersonalBonusTilesPanel, gbc);
        jPersonalBonusTilesPanel.attach(this);


        jPersonalBoardPanel = new PersonalBoardPanel(guiView);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        //jPersonalBoardPanel.setBackground(Color.BLUE);
        //jPersonalBoardPanel.setOpaque(false);
        jPersonalBoardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(jPersonalBoardPanel, gbc);
        jPersonalBoardPanel.attach(this);

        extraCardPanel = new ExtraCardPanel(guiView);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //extraCardPanel.setBackground(Color.green);
        //extraCardPanel.setOpaque(false);
        extraCardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(extraCardPanel, gbc);
        extraCardPanel.attach(this);


        jLeaderCardPanel = new LeaderCardPanel(guiView);

        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //jLeaderCardPanel.setBackground(Color.pink);
        //jLeaderCardPanel.setOpaque(false);
        jLeaderCardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(jLeaderCardPanel, gbc);
        jLeaderCardPanel.attach(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
