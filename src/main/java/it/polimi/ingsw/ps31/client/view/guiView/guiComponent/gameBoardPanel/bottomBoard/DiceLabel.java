package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by giulia on 09/07/2017.
 */
public class DiceLabel extends PaintBackgroundPanel {
    private JLabel label;

    public DiceLabel() {
        addComponentsTopane(this);
    }

    public void addComponentsTopane(Container pane) {


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0};
        gbl.columnWeights = new double[]{0.1, 0.80, 0.1, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.35, 0.30, 0.35, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        label = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(label, gbc);
    }

    public JLabel getLabel() {
        return label;
    }
}
