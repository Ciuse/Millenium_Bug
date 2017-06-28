package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 28/06/2017.
 */
public class DevelopmentCardPanel extends JPanel {
    ActionListener listener;

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




    }
}
