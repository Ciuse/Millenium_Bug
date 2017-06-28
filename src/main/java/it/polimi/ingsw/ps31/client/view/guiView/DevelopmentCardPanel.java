package it.polimi.ingsw.ps31.client.view.guiView;

import it.polimi.ingsw.ps31.client.view.stateView.StateViewTower;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewTowerCardBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.StringReader;

/**
 * Created by giulia on 28/06/2017.
 */
public class DevelopmentCardPanel extends PaintBackgroundPanel {
    ActionListener listener;
    private GuiView guiView;
    private String string;

    public DevelopmentCardPanel(String string) {
        this.string=string;
        addComponentsToPane(this);
    }

    public DevelopmentCardPanel() {
        addComponentsToPane(this);
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0};

        gbl.columnWeights = new double[]{0.52,0.48,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.30,0.454,0.156,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        JButton jButtonPanel = new JButton();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.gridwidth = 1;
        jButtonPanel.setBackground(Color.GREEN);
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(jButtonPanel,gbc);
       super.paintJButton(jButtonPanel,string);

    }


}
