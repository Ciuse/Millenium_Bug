package it.polimi.ingsw.ps31;

import java.util.List;

import static it.polimi.ingsw.ps31.CardColor.YELLOW;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public final class Building extends DevelopmentCard{

    private final GetResourceAction immediateEffect;
    private final GetResourceAction permanentEffect;

    public Building(String name, Period period, List<Resource> cost, GetResourceAction immediateEffect, GetResourceAction permanentEffect) {
        super(name, YELLOW, period, cost);
        this.immediateEffect = immediateEffect;
        this.permanentEffect = permanentEffect;
    }

    public String toString() {
        return "["+this.getName()+"]";
    }
}