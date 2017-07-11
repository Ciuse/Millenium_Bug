package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.topBoard;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.bottomBoard.ActionSpaceBoardButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 26/06/2017.
 * classe che mi rappresenta la parte intermedia della board contenente sia il pannello delle scomuniche che il bottone
 * per l'action space del consiglio
 * @see ExcommunicationPanel
 * @see ActionSpaceBoardButton
 */
public class CouncilPanel extends JPanel implements ActionListener{
    private ActionListener listener;
    private ExcommunicationPanel excommunicationPanel;
    private ActionSpaceBoardButton actionSpaceCouncilPanel;

    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }

    /* Constructor */
    public CouncilPanel() {
        addComponentsToPane(this);
    }


    /**
     * Metodo che mi permette di costruire un layout al JPanel in modo da gestire meglio lo spazio
     */
    public void addComponentsToPane(Container pane) {
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0,0,0,0,0};
        gbl.rowHeights = new int[]{0, 0, 0, 0};

        gbl.columnWeights = new double[]{0.17,0.27,0.07,0.28,0.2099, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.10, 0.22, 0.6799, Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        excommunicationPanel = new ExcommunicationPanel();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        excommunicationPanel.setPreferredSize(new Dimension(10,10));
        excommunicationPanel.setOpaque(false);
        pane.add(excommunicationPanel,gbc);

        actionSpaceCouncilPanel = new ActionSpaceBoardButton();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        actionSpaceCouncilPanel.setName(valueOf(1));
        actionSpaceCouncilPanel.setPreferredSize(new Dimension(10,10));
        actionSpaceCouncilPanel.setContentAreaFilled(false);
        actionSpaceCouncilPanel.addActionListener(this);
        pane.add(actionSpaceCouncilPanel,gbc);

    }

    public ExcommunicationPanel getExcommunicationPanel() {
        return excommunicationPanel;
    }

    public ActionSpaceBoardButton getActionSpaceCouncilPanel() {
        return actionSpaceCouncilPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.actionPerformed(e);
    }
}
