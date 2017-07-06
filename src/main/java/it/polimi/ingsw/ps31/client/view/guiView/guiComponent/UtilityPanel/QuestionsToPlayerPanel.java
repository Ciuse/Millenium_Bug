package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 02/07/2017.
 */
public class QuestionsToPlayerPanel extends JPanel implements DocumentListener{
    private ActionListener listener;

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public QuestionsToPlayerPanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0};
        gbl.rowHeights = new int[]{0,0, 0, 0,0,0};

        gbl.columnWeights = new double[]{0.99999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.03,0.10, 0.02,0.83,0.02, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();


        JTextArea textArea = new JTextArea("inserisci il tuo nome ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        textArea.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        textArea.setPreferredSize(new Dimension(10, 10));
        //textArea.setBackground(Color.RED);
        pane.add(textArea, gbc);
        textArea.getDocument().addDocumentListener(this);
        JScrollPane scrollPane = new JScrollPane(textArea);
        pane.add(scrollPane, gbc);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);


        ChoosenButtonPanel choosenButtonPanel = new ChoosenButtonPanel();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        choosenButtonPanel.setOpaque(false);
        choosenButtonPanel.imageToLoad("/cornice 42.gif");
        gbc.fill = GridBagConstraints.BOTH;
        choosenButtonPanel.setPreferredSize(new Dimension(10, 10));
        pane.add(choosenButtonPanel, gbc);


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
