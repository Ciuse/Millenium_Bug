package it.polimi.ingsw.ps31.Card;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Constants.Period;
import it.polimi.ingsw.ps31.GameThings.Resource;

import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public final class Character extends DevelopmentCard{


    private final ActionExample immediateEffect;
    private final ActionExample permanentEffect;

    public Character(String name, Period period, List<Resource> cost, ActionExample immediateEffect, ActionExample permanentEffect) {
        super(name, CardColor.BLUE, period, cost);
        this.immediateEffect = immediateEffect;
        this.permanentEffect = permanentEffect;
    }

    public String toString() {
        return "["+this.getName()+"]";
    }
}