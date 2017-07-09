package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 07/07/2017.
 */
public class AskActionPanel extends PaintBackgroundPanel implements ActionListener {
    private ActionListener listener;
    private PaintTextArea textArea;
    private JScrollPane scroll;

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public AskActionPanel() {
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane) {


        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0, 0};
        gbl.rowHeights = new int[]{0, 0};

        gbl.columnWeights = new double[]{0.99999, Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.999999, Double.MIN_VALUE};
        pane.setLayout(gbl);


        GridBagConstraints gbc = new GridBagConstraints();


        textArea = new PaintTextArea();
        textArea.setEditable(false);
        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scroll = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        scroll.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        scroll.setPreferredSize(new Dimension(10, 10));
        //textArea.setBackground(Color.RED);
        pane.add(scroll, gbc);



    }

    public void setString(String string){
        this.textArea.append(string+"\n");
    }

        @Override
    public void actionPerformed(ActionEvent e) {

    }
}

