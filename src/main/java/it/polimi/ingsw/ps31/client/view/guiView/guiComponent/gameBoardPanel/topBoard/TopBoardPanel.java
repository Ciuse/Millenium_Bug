package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 26/06/2017.
 */
public class TopBoardPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private TowerPanel towerPanel;
    private CouncilPanel councilPanel;

    public TopBoardPanel() {
        addComponentsToPane(this);
    }


    public void attach(ActionListener listener) {
        this.listener = listener;
    }


    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0};
        gbl.rowHeights = new int[]{0,0,0};

        gbl.columnWeights = new double[]{0.9999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.77, 0.2199, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();


        towerPanel = new TowerPanel();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        towerPanel.setOpaque(false);
       //towerPanel.setBackground(Color.RED);
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
        //councilPanel.setBackground(Color.green);
        //councilPanel.setOpaque(true);
        councilPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(councilPanel, c);
        councilPanel.attach(this);
    }

    public TowerPanel getTowerPanel() {
        return towerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }
}