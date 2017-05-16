package it.polimi.ingsw.ps31.Card;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public final class Character extends DevelopmentCard{


    public Character(int id,String name, int period, List<ResourceList> cost, Effect immediateEffect, Effect permanentEffect) {
        super(id, name, CardColor.BLUE, period, cost, immediateEffect, permanentEffect);
    }
}