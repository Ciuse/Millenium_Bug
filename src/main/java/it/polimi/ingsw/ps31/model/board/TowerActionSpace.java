package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.player.FamilyMember;

/**
 * Created by Francesco on 28/05/2017.
 */
public class TowerActionSpace extends ActionSpace {
    private final TowerCardSpace towerCardSpace;

    /* Constructor */
    public TowerActionSpace(int diceCost, int familyMemberLimit, EffectList immediateEffectList, TowerCardSpace towerCardSpace) {
        super(diceCost, familyMemberLimit, immediateEffectList);
        this.towerCardSpace = towerCardSpace;

    }

    @Override
    public void addFamilyMember(FamilyMember familyMember)
    {
        super.addFamilyMember(familyMember);
        familyMember.getPlayer().getPlayerActionSet().takeCard(towerCardSpace);


    }

    /* Setters & Getters */
    public TowerCardSpace getTowerCardSpace()
    {
        return this.towerCardSpace;
    }
    public  boolean isTowerSpace()
    {
        return this.towerCardSpace != null;
    }
}
