package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.effect.EffectList;

/**
 * Created by Francesco on 12/05/2017.
 *
 * Spazio azione del raccolto grande, il quale non ha un limite di pedine
 */
public class BigHarvest extends Harvest {

    /* Constructor */
    public BigHarvest( int familyMemberLimit, EffectList effectList)
    {
        super(1, familyMemberLimit, effectList);
    }
}
