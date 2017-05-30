package it.polimi.ingsw.ps31.actions;

import it.polimi.ingsw.ps31.board.ActionSpace;
import it.polimi.ingsw.ps31.player.FamilyMember;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Francesco on 26/05/2017.
 */
public abstract class ActionPlaceFamilyMember extends Action{
    protected FamilyMember familyMember = null;

    /* Constructor */
    public ActionPlaceFamilyMember(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
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
}
