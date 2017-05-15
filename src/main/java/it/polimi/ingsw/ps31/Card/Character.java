package it.polimi.ingsw.ps31.Card;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Constants.Period;
import it.polimi.ingsw.ps31.GameThings.Resource;

import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public final class Character extends DevelopmentCard{


    public Character(String name, Period period, List<Resource> cost, Effect immediateEffect, Effect permanentEffect) {
        super(name, CardColor.BLUE, period, cost, immediateEffect, permanentEffect);
    }
}