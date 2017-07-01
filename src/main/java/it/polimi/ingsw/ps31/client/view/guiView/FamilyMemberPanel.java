package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 27/06/2017.
 */
public class FamilyMemberPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private ButtonsFamilyMemberPanel buttonsFamilyMemberPanel;

    public void attach(ActionListener listener) {
        this.listener = listener;
    }

    public FamilyMemberPanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.999999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.07, 0.46, 0.47, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonsFamilyMemberPanel = new ButtonsFamilyMemberPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        //buttonsFamilyMemberPanel.setOpaque(false);
        //buttonsFamilyMemberPanel.setBackground(Color.green);
        pane.add(buttonsFamilyMemberPanel,gbc);

    }

    public ActionListener getListener() {
        return listener;
    }

    public ButtonsFamilyMemberPanel getButtonsFamilyMemberPanel() {
        return buttonsFamilyMemberPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}