package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.gameBoardPanel.trackPanel;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 25/06/2017.
 */
public class VictoryPointTrackFirstColumnPanel extends JPanel {
    private ActionListener listener;


    public void attach (ActionListener listener){
        this.listener=listener;
    }

}
