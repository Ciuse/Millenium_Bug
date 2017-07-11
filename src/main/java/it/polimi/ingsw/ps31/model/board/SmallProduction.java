package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.effect.EffectList;

/**
 * Created by Francesco on 12/05/2017.
 *
 * Spazio azione della produzione piccola, la quale ha un limite di una pedina
 */
public class SmallProduction extends Production {

    /* Constructor */
    public SmallProduction(int familyMemberLimit, EffectList effectList)
    {
        super(1, familyMemberLimit, effectList);
    }
}
