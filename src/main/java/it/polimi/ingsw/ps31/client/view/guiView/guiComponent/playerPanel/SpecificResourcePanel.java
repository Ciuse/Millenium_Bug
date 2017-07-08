package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by giulia on 08/07/2017.
 */
public class SpecificResourcePanel extends PaintBackgroundPanel {
    private PaintBackgroundPanel resourcePanel;
    private JLabel resource;

    public SpecificResourcePanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0,0,0};
        gbl.rowHeights = new int[]{0, 0,0,0};

        gbl.columnWeights = new double[]{0.09,0.09, 0.1533,0.33, 0.33, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.43, 0.13, 0.43, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();


        resourcePanel = new PaintBackgroundPanel();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        resourcePanel.imageToLoad("/sfondorisorse.jpg");
        resourcePanel.setOpaque(false);
        resource = new JLabel();
        resourcePanel.add(resource);
        gbc.fill = GridBagConstraints.BOTH;
        pane.add(resourcePanel, gbc);
    }



    public PaintBackgroundPanel getResourcePanel() {
        return resourcePanel;
    }

    public JLabel getResource() {
        return resource;
    }
}
