package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.effect.EffectList;

/**
 * Created by Francesco on 12/05/2017.
 */
public class SmallHarvest extends Harvest {

    /* Constructor */
    public SmallHarvest(int familyMemberLimit, EffectList effectList)
    {
        super(1, familyMemberLimit, effectList);
    }
}
