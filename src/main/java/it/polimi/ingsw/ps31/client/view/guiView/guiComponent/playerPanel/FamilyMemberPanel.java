package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 27/06/2017.
 * rappresenta la classe che mi contiene il pannello che a sua volta contiene il ButtonFamilyMemberpanel(contiene i 4 bottoni
 * dei family member di ciascun giocatore)
 */
public class FamilyMemberPanel extends JPanel implements ActionListener {
    private ActionListener listener;
    private GuiView guiView;
    private ButtonsFamilyMemberPanel buttonsFamilyMemberPanel;

    /* Constructor */
    public FamilyMemberPanel(GuiView guiView) {
        this.guiView = guiView;
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
     */
    public void addComponentsToPane(Container pane) {
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0};
        gbl.rowHeights = new int[]{0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.999999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.08, 0.73, 0.18999, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        buttonsFamilyMemberPanel = new ButtonsFamilyMemberPanel(guiView);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        buttonsFamilyMemberPanel.setOpaque(false);
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