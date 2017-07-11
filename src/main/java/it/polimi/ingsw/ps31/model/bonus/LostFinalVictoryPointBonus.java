package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.gameResource.PointResource;
import it.polimi.ingsw.ps31.model.player.Player;


/**
 * Created by giulia on 16/06/2017.
 *
 * Malus che influenza il conteggio finale dei punti del giocatore, non facendoti contare i punti derivanti
 * dal numero di punti vittoria che ha
 *
 * @see it.polimi.ingsw.ps31.model.game.GameUtility
 */
public class LostFinalVictoryPointBonus extends  Bonus {
    private final PointResource pointResource;


    public LostFinalVictoryPointBonus(PointResource pointResource) {
        this.pointResource = pointResource;
    }

    public PointResource getPointResource() {
        return pointResource;
    }

    @Override
    public void activate(Player player) {

    }
}