package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public abstract class Action extends ModelChoices implements PlayerActions{
    protected final Player player;
    protected final  ActionControlSet actionControlSet;

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
