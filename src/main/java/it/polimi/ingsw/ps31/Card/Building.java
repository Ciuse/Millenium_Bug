package it.polimi.ingsw.ps31.Card;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public final class Building extends DevelopmentCard{


    public Building(int id, String name, int period, List<ResourceList> cost, List<Effect> immediateEffect, List<Effect> permanentEffect) {
        super(id, name, CardColor.YELLOW, period, cost, immediateEffect, permanentEffect);
    }
}