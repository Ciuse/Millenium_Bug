package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Effect.EffectList;
import it.polimi.ingsw.ps31.Player.FamilyMember;

/**
 * Created by Francesco on 12/05/2017.
 */
public class ActionSpace extends PhysicalActionBox {

    private final int diceCost;
    private final int familyMemberLimit; //Limite massimo di familiari nello spazio azione. -1 indica l'assenza di limite
    private FamilyMember familyMember;
    private final EffectList immediateEffectList;

    /* Constructor */
    public ActionSpace(int diceCost, int familyMemberLimit, EffectList immediateEffectList) {
        this.diceCost = diceCost;
        this.familyMemberLimit = familyMemberLimit;
        this.immediateEffectList = immediateEffectList;
        this.familyMember = null;
    }

    /* Getters & Setters */
    public int getDiceCost()
    {
        return this.diceCost;
    }

    public int getFamilyMemberLimit()
    {
        return this.familyMemberLimit;
    }

    public FamilyMember getFamilyMember()
    {
        return this.familyMember;
    }

    public EffectList getImmediateEffectList()
    {
        return this.immediateEffectList;
    }

    public void setFamilyMember(FamilyMember familyMember)
    {
        this.familyMember=familyMember;
    }


}
