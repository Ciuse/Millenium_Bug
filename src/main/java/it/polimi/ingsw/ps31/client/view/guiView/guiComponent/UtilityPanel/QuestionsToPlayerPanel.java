package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 02/07/2017.
 */
public class QuestionsToPlayerPanel extends JPanel implements ActionListener {
    private ActionListener listener;

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void addComponentsToPane(Container pane) {


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0, 0, 0};
        gbl.rowHeights = new int[]{0, 0, 0};

        gbl.columnWeights = new double[]{0.50, 0.018999, 0.481, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.528, 0.472, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();


        JTextField textField1 = new JTextField("inserisci il tuo nome");
        JTextArea textArea = new JTextArea("Hello World!");
        JTextField textField2 = new JTextField("ciaooo");
        JScrollPane scrollPane = new JScrollPane(textArea);

        textField1.setOpaque(false);
        textArea.setOpaque(false);
        textField2.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);


        pane.add(textField1, BorderLayout.NORTH);
        pane.add(scrollPane, BorderLayout.CENTER);
        pane.add(textField2, BorderLayout.SOUTH);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
