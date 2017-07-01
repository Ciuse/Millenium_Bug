package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 26/06/2017.
 */
public class UtilityPanel extends JPanel implements ActionListener {
    private GuiView guiView;

    public UtilityPanel(GuiView guiView) {
        this.guiView=guiView;
        this.setBackground(new Color(45,55,105));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
