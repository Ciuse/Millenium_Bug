package it.polimi.ingsw.ps31.client.view.guiView;

import it.polimi.ingsw.ps31.model.card.DevelopmentCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by giulia on 26/06/2017.
 */
public class TowerPanel extends JPanel implements ActionListener {
    ActionListener listener;


    public void attach (ActionListener listener){
        this.listener=listener;
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0,0};


        gbl.columnWeights = new double[]{0.046,0.22,0.22,0.22,0.2299,0.05,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.25,0.25,0.25,0.2499,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel[][] panels = new DevelopmentCardPanel[4][4];
        for (int i =0;i<4;i++){
            for(int j=0;j<4;j++){
                panels[i][j]=new DevelopmentCardPanel();
                gbc.gridx = i+1;
                gbc.gridy = j;
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                panels[i][j].setBackground(Color.RED);
                gbc.fill = gbc.BOTH;
                panels[i][j].setOpaque(false);
                pane.add(panels[i][j],gbc);

            }
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
