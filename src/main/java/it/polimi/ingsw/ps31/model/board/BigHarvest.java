package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.effect.EffectList;

/**
 * Created by Francesco on 12/05/2017.
 */
public class BigHarvest extends Harvest {

    /* Constructor */
    public BigHarvest( int familyMemberLimit, EffectList effectList)
    {
        super(1, familyMemberLimit, effectList);
    }
}
