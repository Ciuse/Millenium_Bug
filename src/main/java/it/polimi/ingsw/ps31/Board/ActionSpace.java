package it.polimi.ingsw.ps31.Board;

/**
 * Created by Francesco on 12/05/2017.
 */
public class ActionSpace extends PhysicalActionBox {

    private final int diceCost;
    private final int familyMemberLimit; //Limite massimo di familiari nello spazio azione. -1 indica l'assenza di limite
    private FamilyMember familyMember;
    private final Effect immediateEffect;

    /* Constructor */
    public ActionSpace(int diceCost, int familyMemberLimit, Effect immediateEffect) {
        this.diceCost = diceCost;
        this.familyMemberLimit = familyMemberLimit;
        this.immediateEffect = immediateEffect;

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

    public Effect getImmediateEffect()
    {
        return this.immediateEffect;
    }

    public void setFamilyMember(FamilyMember familyMember)
    {
        this.familyMember=familyMember;
    }


}
