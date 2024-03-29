package it.polimi.ingsw.ps31.model.card;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 *
 * Carta Verde
 */
public final class Territory extends DevelopmentCard {

    public Territory(int id, String name, int period, List<ResourceList> cost, EffectList immediateEffect, EffectList permanentEffect) {
        super(id, name, CardColor.GREEN, period, cost, immediateEffect, permanentEffect);
    }

    //Generic "GREEN CARD" -> for leader card cost
    public Territory() {
        super(0, null, CardColor.GREEN, 0, null, null, null);
    }

}
