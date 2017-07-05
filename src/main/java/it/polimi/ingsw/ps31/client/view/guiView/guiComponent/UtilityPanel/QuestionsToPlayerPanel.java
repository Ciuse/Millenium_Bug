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
        gbl.rowHeights = new int[]{0,0, 0, 0,0};

        gbl.columnWeights = new double[]{0.45,0.55, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.20,0.30, 0.10,0.40, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();


        JTextArea textArea = new JTextArea("inserisci il tuo nome ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        textArea.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        textArea.setPreferredSize(new Dimension(10, 10));
        //textArea.setBackground(Color.RED);
        JLabel label = new JLabel("inserisci il tuo nome");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        label.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        label.setPreferredSize(new Dimension(10, 10));
        //label.setBackground(Color.RED);
        textArea.add(label);
        pane.add(textArea, gbc);
        textArea.getDocument().addDocumentListener(this);
        JScrollPane scrollPane = new JScrollPane(textArea);
        pane.add(scrollPane, gbc);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);


        JTextField textField1 = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        textField1.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        textField1.setPreferredSize(new Dimension(10, 10));
        textField1.setBackground(Color.RED);
        pane.add(textField1, gbc);
        textField1.getDocument().addDocumentListener(this);


        ChoosenButtonPanel choosenButtonPanel = new ChoosenButtonPanel();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        choosenButtonPanel.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        choosenButtonPanel.setPreferredSize(new Dimension(10, 10));
        //choosenButtonPanel.setBackground(Color.RED);
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
