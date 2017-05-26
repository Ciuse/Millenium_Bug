package it.polimi.ingsw.ps31.ActionControls;

import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 24/05/2017.
 */
public abstract class Control {
    protected static Player player;

    public abstract boolean execute();

    public Control(Player player)
    {
        this.player = player;
    }
}
