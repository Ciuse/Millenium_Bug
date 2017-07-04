package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 04/07/2017.
 */
public class ChoosenButtonPanel extends JPanel implements ActionListener {
    private ActionListener listener;

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public ChoosenButtonPanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0};
        gbl.rowHeights = new int[]{0, 0};

        gbl.columnWeights = new double[]{0.9999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.999999, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();

        JButton button = new JButton();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        button.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        button.setPreferredSize(new Dimension(10, 10));
        button.setBackground(Color.RED);
        pane.add(button, gbc);
        button.addActionListener(this);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
