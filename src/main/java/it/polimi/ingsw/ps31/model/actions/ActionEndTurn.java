package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 07/06/2017.
 *
 * Azione che permette al giocatore di finire il turno dopo aver piazzato un famigliare, o nel caso gli
 * rimanga solo il famigliare neutro.
 *
 */
public class ActionEndTurn extends Action {
    private boolean active =false;
    private boolean used = false;

    public ActionEndTurn(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * L'attivazione setta il booleano del player che indica che vuole finire il turno
     */
    @Override
    public void activate() {
        player.setWannaEndTurn(true);

    }

    @Override
    public String toString() {
        return "End Turn";
    }

    public boolean isUsed() {
        return used;
    }
}
