package it.polimi.ingsw.ps31.client.view.guiView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 27/06/2017.
 */
public class PersonalBoardPanel extends JPanel {
    ActionListener listener;
    private Image image;


    public PersonalBoardPanel(Image image) {
        this.image = image;

    }


    public void attach (ActionListener listener){
        this.listener=listener;
    }
}
