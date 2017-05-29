package it.polimi.ingsw.ps31.card;

import it.polimi.ingsw.ps31.constants.CardColor;
import it.polimi.ingsw.ps31.effect.EffectList;
import it.polimi.ingsw.ps31.gameThings.ResourceList;

import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public final class Character extends DevelopmentCard{


    public Character(int id, String name, int period, List<ResourceList> cost, EffectList immediateEffect, EffectList permanentEffect) {
        super(id, name, CardColor.BLUE, period, cost, immediateEffect, permanentEffect);
    }
}