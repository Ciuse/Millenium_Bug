package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.player.FamilyMember;

/**
 * Created by Francesco on 12/05/2017.
 */
public abstract class Harvest extends ActionSpace {

    public Harvest(int diceCost, int familyMemberLimit, EffectList effectList)
    {
        super(diceCost, familyMemberLimit, effectList);
}

    @Override
    public void addFamilyMember(FamilyMember familyMember) {
    }
}
