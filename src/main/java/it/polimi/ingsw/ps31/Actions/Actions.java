package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public abstract class Actions implements PlayerActions{

    protected static Player player;

    public Actions(Player player)
    {
        if(player == null)
        {
            //TODO: fare qualcosa (eccezione?)
        }

        this.player = player;
    }

}
