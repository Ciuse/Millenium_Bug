package it.polimi.ingsw.ps31;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.CardColor.GREEN;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public final class Territory extends DevelopmentCard {

    private final GetResourceAction immediateEffect;
    private final GetResourceAction permanentEffect;

    public Territory(String name, Period period, List<Resource> cost, GetResourceAction immediateEffect, GetResourceAction permanentEffect) {
        super(name, GREEN, period, cost);
        this.immediateEffect = immediateEffect;
        this.permanentEffect = permanentEffect;
    }

    public Territory(){
        this.immediateEffect=null;
        this.permanentEffect=null;
    }

    public GetResourceAction getImmediateEffect() {
        return this.immediateEffect;
    }

    public GetResourceAction getPermanentEffect() {
        this.getPeriod();
        return this.permanentEffect;

    }

    public String toString() {
        return "["+this.getName()+"]";
    }

}
