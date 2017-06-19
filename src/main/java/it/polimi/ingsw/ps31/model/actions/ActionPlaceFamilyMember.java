package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Francesco on 26/05/2017.
 */
public abstract class ActionPlaceFamilyMember extends Action{
    private boolean used = false;
    protected FamilyMember familyMember = null;
    protected List<Integer> defaultDenyActionSpaces;

    /* Constructor */
    public ActionPlaceFamilyMember(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
        this.defaultDenyActionSpaces= new ArrayList<>();
    }

    /* Getters & Setters */
    public void setFamilyMember(FamilyMember familyMember)
    {
        if( familyMember.isPlaced() )
        {
            //TODO: gestire (eccezione?)
        }
        else
            this.familyMember = familyMember;
    }

    public void resetFamilyMember()
    {
        this.familyMember = null;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    /* Modifiers */
    public void addDefaultDenyActionSpace(List<Integer> actionSpaceId)
    {
        if ( this.defaultDenyActionSpaces.contains(actionSpaceId) )
            return;

        this.defaultDenyActionSpaces.addAll(actionSpaceId);
    }

}
