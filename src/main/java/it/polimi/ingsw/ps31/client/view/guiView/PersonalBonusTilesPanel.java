package it.polimi.ingsw.ps31.client.view.guiView;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by giulia on 27/06/2017.
 */
public class PersonalBonusTilesPanel extends PaintBackgroundPanel {
    private ActionListener listener;

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void paintComponent(Graphics g) {
        super.imageToLoad("/personalbonustile_2.png");
        super.paintComponent(g);
    }
}
