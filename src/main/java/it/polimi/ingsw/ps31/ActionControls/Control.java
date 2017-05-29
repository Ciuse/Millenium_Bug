package it.polimi.ingsw.ps31.ActionControls;

import it.polimi.ingsw.ps31.Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 24/05/2017.
 */
public abstract class Control {
    protected static Player player;
    protected List<Boolean> conditions = new ArrayList<Boolean>();

    public abstract boolean execute();

    public Control(Player player)
    {
        this.player = player;
    }
}
