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

    /* Constructor */
    public PersonalBonusTilesPanel() {
    }
    /**
     *metodo che mi permette di attaccare questa classe al suo listener
     */
    public void attach (ActionListener listener){
        this.listener=listener;
    }

    /**
     *metodo che mi permette di stampare il personal bonus tiles una volta che il giocatore lo ha scelto
     */
    public void printTiles(StateViewPersonalBonusTiles personalBonusTiles){
        imageToReprint("/player/personalbonustileBoard_" + valueOf(personalBonusTiles.getPersonalBonusTilesId()) + ".png");
    }

}
