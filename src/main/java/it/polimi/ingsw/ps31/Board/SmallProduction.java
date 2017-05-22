package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Effect.EffectList;

/**
 * Created by Francesco on 12/05/2017.
 */
public class SmallProduction extends Production {

    /* Constructor */
    public SmallProduction(int familyMemberLimit, EffectList effectList)
    {
        super(1, familyMemberLimit, effectList);
    }
}
