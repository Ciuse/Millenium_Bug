package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 26/06/2017.
 * questa classe rappresenta la parte superiore della board e comprende sia le torri che il palazzo del consiglio
 * @see TowerPanel
 * @see CouncilPanel
 */
public class TopBoardPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private TowerPanel towerPanel;
    private CouncilPanel councilPanel;


    /* Constructor */
    public TopBoardPanel() {
        addComponentsToPane(this);
    }


    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach(ActionListener listener) {
        this.listener = listener;
    }


    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     * lo invoco all'interno del costruttore del pannello così quando si crea si crea con il layout
     */
    public void addComponentsToPane(Container pane) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0};
        gbl.rowHeights = new int[]{0,0,0};

        gbl.columnWeights = new double[]{0.9999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.75, 0.2399, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();


        towerPanel = new TowerPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        towerPanel.setOpaque(false);
        c.fill = GridBagConstraints.BOTH;
        towerPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(towerPanel, c);
        towerPanel.attach(this);



        councilPanel = new CouncilPanel();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        councilPanel.setOpaque(false);
        c.fill = GridBagConstraints.BOTH;
        councilPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(councilPanel, c);
        councilPanel.attach(this);
    }

    public CouncilPanel getCouncilPanel() {
        return councilPanel;
    }

    public TowerPanel getTowerPanel() {
        return towerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }
}