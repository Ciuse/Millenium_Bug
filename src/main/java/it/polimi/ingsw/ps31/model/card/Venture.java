package it.polimi.ingsw.ps31.model.card;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 *
 * Carta Viola
 */
public final class Venture extends DevelopmentCard{


    public Venture(int id, String name, int period, List<ResourceList> cost, EffectList immediateEffect, EffectList permanentEffect) {
        super(id, name, CardColor.PURPLE, period, cost, immediateEffect, permanentEffect);
    }

    //Generic "PURPLE CARD" -> for leader card cost
    public Venture() {
        super(0, null, CardColor.PURPLE, 0, null, null, null);
    }
}
