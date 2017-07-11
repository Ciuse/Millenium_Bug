package it.polimi.ingsw.ps31.model.card;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 *
 * Carta Gialla
 */
public final class Building extends DevelopmentCard{


    public Building(int id, String name, int period, List<ResourceList> cost, EffectList immediateEffect, EffectList permanentEffect) {
        super(id, name, CardColor.YELLOW, period, cost, immediateEffect, permanentEffect);
    }

    //Generic "YELLOW CARD" -> for leader card cost
    public Building() {
        super(0, null, CardColor.YELLOW, 0, null, null, null);
    }
}