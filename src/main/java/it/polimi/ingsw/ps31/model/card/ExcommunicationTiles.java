package it.polimi.ingsw.ps31.model.card;

import it.polimi.ingsw.ps31.model.bonus.Bonus;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 15/05/2017.
 */
public class ExcommunicationTiles{
    private final int period;
    private final Bonus permanentMalus;
    private final boolean endGame;

    public ExcommunicationTiles(int period, Bonus permanentMalus, boolean endGame) {
        this.period = period;
        this.permanentMalus = permanentMalus;
        this.endGame = endGame;
    }

    public void setExcommunicationToPlayer(Player player){
        player.addExcommunication(this);        //TODO: VERIFICARE
    }

    public int getPeriod() {
        return period;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public Bonus getPermanentMalus() {
        return permanentMalus;
    }
}
