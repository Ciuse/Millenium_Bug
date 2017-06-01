package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.effect.ActiveEffect;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class ActionSpace implements PhysicalSpaceBehavior, ActiveEffect {

    private final int diceCost;
    private final int familyMemberLimit; //Limite massimo di familiari nello spazio azione. -1 indica l'assenza di limite
    private List<FamilyMember> familyMembers;
    private final EffectList immediateEffectList;

    /* Constructor */
    public ActionSpace(int diceCost, int familyMemberLimit, EffectList immediateEffectList) {
        this.diceCost = diceCost;
        this.familyMemberLimit = familyMemberLimit;
        this.immediateEffectList = immediateEffectList;
        this.familyMembers = null;
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

    public List<FamilyMember> getFamilyMemberList()
    {
        return this.familyMembers;
    }

    public FamilyMember getFamilyMemberIndex(int index)
    {
        if ( index >= this.familyMembers.size())
            return null; //Indice maggiore della dimensione della lista
        else
            return this.familyMembers.get(index);
    }

    public EffectList getImmediateEffectList()
    {
        return this.immediateEffectList;
    }

    public void addFamilyMember(FamilyMember familyMember)
    {
        this.familyMembers.add(familyMember);
    }


    @Override
    public void activeEffectList(Player player) {
        if(this.immediateEffectList!=null){
            for(int i=0; i<this.immediateEffectList.size();i++){
                this.immediateEffectList.get(i).activate(player);
            }
        }
    }
}
