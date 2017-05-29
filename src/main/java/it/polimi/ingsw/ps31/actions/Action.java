package it.polimi.ingsw.ps31.actions;

import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public abstract class Action implements PlayerActions{
    protected static Player player;
    protected static  ActionControlSet actionControlSet;

    public Action(Player player, ActionControlSet actionControlSet)
    {
        if(player == null)
        {
            //TODO: fare qualcosa (eccezione?)
        }

        this.player = player;
        this.actionControlSet = actionControlSet;
    }

}
