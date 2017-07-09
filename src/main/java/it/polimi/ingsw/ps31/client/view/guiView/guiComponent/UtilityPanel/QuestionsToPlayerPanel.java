package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.GuiView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 02/07/2017.
 */
public class QuestionsToPlayerPanel extends JPanel implements DocumentListener{
    private GuiView guiView;
    private ActionListener listener;
    private AskActionPanel askActionPanel;
    private ChosenActionButtonPanel chosenActionButtonPanel;

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public QuestionsToPlayerPanel(GuiView guiView) {

        this.guiView = guiView;
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0};
        gbl.rowHeights = new int[]{0,0, 0, 0,0,0};

        gbl.columnWeights = new double[]{0.99999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.05,0.08, 0.02,0.83,0.02, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();


        askActionPanel = new AskActionPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        askActionPanel.setOpaque(false);
        askActionPanel.imageToLoad("/sfondoPerLeDomande.jpg");
        gbc.fill = GridBagConstraints.BOTH;
        askActionPanel.setPreferredSize(new Dimension(10, 10));
        //askActionPanel.setBackground(Color.RED);
        pane.add(askActionPanel, gbc);



        chosenActionButtonPanel = new ChosenActionButtonPanel(guiView);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        chosenActionButtonPanel.setOpaque(false);
        chosenActionButtonPanel.imageToLoad("/cornice 42.gif");
        gbc.fill = GridBagConstraints.BOTH;
        chosenActionButtonPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(chosenActionButtonPanel, gbc);


    }

    public AskActionPanel getAskActionPanel() {
        return askActionPanel;
    }

    public ChosenActionButtonPanel getChosenActionButtonPanel() {
        return chosenActionButtonPanel;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
