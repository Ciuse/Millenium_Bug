package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 27/06/2017.
 */
public class PersonalBoardPanel extends JPanel {
    ActionListener listener;

    public PersonalBoardPanel() {
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }
}
