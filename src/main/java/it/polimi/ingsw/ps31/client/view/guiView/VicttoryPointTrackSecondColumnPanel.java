package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 26/06/2017.
 */
public class VicttoryPointTrackSecondColumnPanel extends JPanel {
    ActionListener listener;


    public void attach (ActionListener listener){
        this.listener=listener;
    }
}