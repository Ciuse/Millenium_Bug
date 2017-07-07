package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.UtilityPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 07/07/2017.
 */
public class AskActionPanel extends PaintBackgroundPanel implements ActionListener {
    private ActionListener listener;
    private JLabel textArea;

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


        textArea = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        textArea.setOpaque(false);
        gbc.fill = GridBagConstraints.BOTH;
        textArea.setPreferredSize(new Dimension(10, 10));
        //textArea.setBackground(Color.RED);
        pane.add(textArea, gbc);



    }

    public void setString(String string){
        this.textArea.setText(string);
    }

        @Override
    public void actionPerformed(ActionEvent e) {

    }
}

