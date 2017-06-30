package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 29/06/2017.
 */
public class PlayerResourcesPanel extends JPanel implements ActionListener{
    ActionListener listener;


    public void attach (ActionListener listener){
        this.listener=listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
