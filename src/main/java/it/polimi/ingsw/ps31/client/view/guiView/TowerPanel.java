package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 26/06/2017.
 */
public class TowerPanel extends JPanel {
    ActionListener listener;


    public void attach (ActionListener listener){
        this.listener=listener;
        addComponentsToPane(this);
    }

    public void addComponentsToPane(Container pane){
        //griglia 4*5
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[]{0,0,0,0,0};
        gbl.rowHeights = new int[]{0,0,0,0,0};


        gbl.columnWeights = new double[]{0.16,0.16,0.16,0.16,Double.MIN_VALUE};
        gbl.rowWeights = new double[]{0.16,0.16,0.16,0.16,0.16,Double.MIN_VALUE};
        pane.setLayout(gbl);

        GridBagConstraints c = new GridBagConstraints();


        //Object lista[][] =
                //{{null,new JLabel(new ImageIcon("C:\\Users\\giulia\\Desktop\\LorenzoCards_compressed_png\\devcards_f_en_c_2.png"))},
                  //      {null,new JLabel(new ImageIcon("C:\\Users\\giulia\\Desktop\\LorenzoCards_compressed_png\\devcards_f_en_c_3.png"))}
    //};

      //  JButton jButton1= new JButton();
        //jButton1=(JButton)lista[1][1];
        //this.add(jButton1);
        //JButton jButton2= new JButton();
        //jButton2=(JButton)lista[2][2];
        //this.add(jButton2);

    }


}
