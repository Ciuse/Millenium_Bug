package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 24/05/2017.
 */
public abstract class Control{
    protected final Player player;

    public abstract boolean execute();

    public Control(Player player)
    {
        this.player = player;
    }

    public abstract String getControlStringError();
}
