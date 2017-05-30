package it.polimi.ingsw.ps31.actionControls;

import it.polimi.ingsw.ps31.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 24/05/2017.
 */
public abstract class Control {
    protected final Player player;
    protected List<Boolean> conditions = new ArrayList<>();

    public abstract boolean execute();

    public Control(Player player)
    {
        this.player = player;
    }
}
