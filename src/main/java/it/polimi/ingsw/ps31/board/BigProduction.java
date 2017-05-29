package it.polimi.ingsw.ps31.board;
import it.polimi.ingsw.ps31.effect.EffectList;

/**
 * Created by Francesco on 12/05/2017.
 */
public class BigProduction extends Production {

    /* Constructor */
    public BigProduction(int familyMemberLimit, EffectList effectList)
    {
        super(1, familyMemberLimit, effectList);
    }
}
