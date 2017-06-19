package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public class ActionActiveEndButton extends Action {
    private boolean active =false;
    private boolean used = false;
    //TODO IMPLEMENTARE

    public ActionActiveEndButton(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public void activate() {
    }

    @Override
    public String toString() {
        return "End Turn";
    }

    public boolean isUsed() {
        return used;
    }
}
