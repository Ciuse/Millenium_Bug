package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel.UtilityPanel;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.GameBoardPanel;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel.PlayerPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 30/06/2017.
 */
public class BackgroundMainFramePanel extends PaintBackgroundPanel implements ActionListener{
    private GameBoardPanel gameBoardPanel;
    private PlayerPanel playerPanel;
    private UtilityPanel utilityPanel;
    private GuiView guiView;

    public void paintComponent(Graphics g) {
        super.imageToLoad("/punchboard_b_c_4.jpg");
        super.paintComponent(g);
    }

    public BackgroundMainFramePanel(GuiView guiView) {
        this.guiView=guiView;
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {



        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0};

        gbl.columnWeights = new double[]{0.50, 0.018999, 0.481, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.528, 0.472, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();

        gameBoardPanel=new GameBoardPanel(guiView);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gameBoardPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(gameBoardPanel, gbc);

        playerPanel = new PlayerPanel(guiView);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        playerPanel.setOpaque(false);
        playerPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(playerPanel, gbc);

        utilityPanel = new UtilityPanel(guiView);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        utilityPanel.setOpaque(false);
        utilityPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(utilityPanel, gbc);


    }

    public GameBoardPanel getGameBoardPanel() {
        return gameBoardPanel;
    }

    public PlayerPanel getPlayerPanel() {
        return playerPanel;
    }

    public UtilityPanel getUtilityPanel() {
        return utilityPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
