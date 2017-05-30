package it.polimi.ingsw.ps31.player;

import it.polimi.ingsw.ps31.effect.Effect;
import it.polimi.ingsw.ps31.effect.EffectList;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 28/05/2017.
 */
public abstract class HarvestProductionList {
    private  final Player player;

    /* Constructor */
    public HarvestProductionList(Player player) {

        this.player = player;
    }

    /* Class Methods */
    public abstract void activate(int diceValue);


}
