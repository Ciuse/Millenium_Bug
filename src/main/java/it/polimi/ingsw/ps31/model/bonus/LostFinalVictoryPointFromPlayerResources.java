package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 *
 * Malus che influenza il conteggio finale dei punti del giocatore, non facendoti contare i punti derivanti
 * dal numero di risorse che il player possieda alla fine del gioco
 *
 * @see it.polimi.ingsw.ps31.model.game.GameUtility
 */
public class LostFinalVictoryPointFromPlayerResources extends Bonus {
    private final String string = "LostFinalVictoryPointFromPlayerResources";

    public LostFinalVictoryPointFromPlayerResources() {
       
    }

    public String getString() {
        return string;
    }

    @Override
    public void activate(Player player) {

    }
}
