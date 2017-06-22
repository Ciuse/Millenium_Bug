package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 18/05/2017.
 */
public abstract class Action extends ModelChoices implements PlayerActions{
    protected final Player player;
    protected final  ActionControlSet actionControlSet;
    private List<Action> actionList= new ArrayList<>();

    public Action(Player player, ActionControlSet actionControlSet)
    {
        if(player == null)
        {
            //TODO: fare qualcosa (eccezione?)
        }

        this.player = player;
        this.actionControlSet = actionControlSet;
        actionList.add(this);
    }

}
