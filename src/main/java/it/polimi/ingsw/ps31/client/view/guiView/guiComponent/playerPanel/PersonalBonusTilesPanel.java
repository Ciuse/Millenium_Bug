package it.polimi.ingsw.ps31.client.view.guiView.guiComponent.playerPanel;

import it.polimi.ingsw.ps31.client.view.guiView.guiComponent.other.PaintBackgroundPanel;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBonusTiles;

import java.awt.event.ActionListener;

import static java.lang.String.valueOf;

/**
 * Created by giulia on 27/06/2017.
 */
public class PersonalBonusTilesPanel extends PaintBackgroundPanel {
    private ActionListener listener;


    public PersonalBonusTilesPanel() {
    }

    public void attach (ActionListener listener){
        this.listener=listener;
    }

    public void printTiles(StateViewPersonalBonusTiles personalBonusTiles){
        this.setOpaque(true);
        imageToReprint("/personalbonustile_" + valueOf(personalBonusTiles.getPersonalBonusTilesId()) + ".png");
    }

}
