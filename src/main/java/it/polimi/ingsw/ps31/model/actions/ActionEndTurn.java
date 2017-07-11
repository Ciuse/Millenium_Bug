package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 07/06/2017.
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
