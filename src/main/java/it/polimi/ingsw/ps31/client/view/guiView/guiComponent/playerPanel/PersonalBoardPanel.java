package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 27/06/2017.
 */
public class PersonalBoardPanel extends PaintBackgroundPanel implements ActionListener{
    private ActionListener listener;
    private  GuiView guiView;
    private DevelopmentCardsOpenedPanel cardYellow;
    private DevelopmentCardsOpenedPanel cardGreen;
    private PlayerResourcesPanel playerResourcesPanel;


    public void paintComponent(Graphics g) {
        super.imageToLoad("/player/personalBoard.png");
        super.paintComponent(g);
    }


    public PersonalBoardPanel(GuiView guiView) {
        this.guiView = guiView;
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0};
        gbl.rowHeights = new int[]{0,0, 0, 0, 0, 0,0,0,0};


        gbl.columnWeights = new double[]{0.06,0.88,0.06, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.07,0.31, 0.06, 0.31, 0.07,0.08,0.063,0.037 ,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        cardYellow = new DevelopmentCardsOpenedPanel(guiView);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        //cardYellow.setBackground(Color.CYAN);
        cardYellow.setOpaque(false);
        cardYellow.setPreferredSize(new Dimension(10,10));
        pane.add(cardYellow, gbc);
        cardYellow.attach(this);

        cardGreen = new DevelopmentCardsOpenedPanel(guiView);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        //cardGreen.setBackground(Color.red);
        cardGreen.setOpaque(false);
        cardGreen.setPreferredSize(new Dimension(10,10));
        pane.add(cardGreen, gbc);
        cardGreen.attach(this);

        playerResourcesPanel = new PlayerResourcesPanel();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //playerResourcesPanel.setBackground(Color.green);
        playerResourcesPanel.setOpaque(false);
        playerResourcesPanel.setPreferredSize(new Dimension(10,10));
        pane.add(playerResourcesPanel, gbc);
        playerResourcesPanel.attach(this);


    }

    public PlayerResourcesPanel getPlayerResourcesPanel() {
        return playerResourcesPanel;
    }

    public DevelopmentCardsOpenedPanel getCardYellow() {
        return cardYellow;
    }

    public DevelopmentCardsOpenedPanel getCardGreen() {
        return cardGreen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
