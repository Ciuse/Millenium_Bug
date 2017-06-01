package it.polimi.ingsw.ps31.model.board;
import it.polimi.ingsw.ps31.model.effect.EffectList;

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
