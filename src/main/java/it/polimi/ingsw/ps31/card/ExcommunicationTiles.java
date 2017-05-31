package it.polimi.ingsw.ps31.card;

import it.polimi.ingsw.ps31.bonus.Bonus;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Francesco on 15/05/2017.
 */
public class ExcommunicationTiles {
    private final int period;
    private final Bonus permanentMalus;

    public ExcommunicationTiles(int period, Bonus permanentMalus) {
        this.period = period;
        this.permanentMalus = permanentMalus;
    }

    public void setExcommunicationToPlayer(Player player){
        player.addExcommunication(this);        //TODO: VERIFICARE
    }
}