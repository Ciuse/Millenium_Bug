package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Effect.EffectList;
import it.polimi.ingsw.ps31.Player.FamilyMember;

/**
 * Created by Francesco on 12/05/2017.
 */
public abstract class Production extends ActionSpace {

    public Production(int diceCost, int familyMemberLimit, EffectList effectList)
    {
        super(diceCost, familyMemberLimit, effectList);    //TODO: specificare l'effetto immediato
    }
}
