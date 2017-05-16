package it.polimi.ingsw.ps31.Card;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public final class Territory extends DevelopmentCard {

    public Territory(String name, int period, List<ResourceList> cost, Effect immediateEffect, Effect permanentEffect) {
        super(name, CardColor.GREEN, period, cost, immediateEffect, permanentEffect);
    }



}
