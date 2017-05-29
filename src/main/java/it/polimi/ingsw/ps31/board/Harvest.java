package it.polimi.ingsw.ps31.board;

import it.polimi.ingsw.ps31.effect.EffectList;
import it.polimi.ingsw.ps31.player.FamilyMember;

/**
 * Created by Francesco on 12/05/2017.
 */
public abstract class Harvest extends ActionSpace {

    public Harvest(int diceCost, int familyMemberLimit, EffectList effectList)
    {
        super(diceCost, familyMemberLimit, effectList);    //TODO: l'effetto immediato è l'attivazione del raccolto
}

    @Override
    public void addFamilyMember(FamilyMember familyMember) {
        super.addFamilyMember(familyMember);
        familyMember.getPlayer().getPlayerActionSet().activateHarvest(familyMember.getTotalValue());
    }
}
