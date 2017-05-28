package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.Board.ActionSpace;
import it.polimi.ingsw.ps31.Player.FamilyMember;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 26/05/2017.
 */
public abstract class ActionPlaceFamilyMember extends Action{
    protected ActionSpace towerActionSpace = null;
    protected FamilyMember familyMember = null;

    /* Constructor */
    public ActionPlaceFamilyMember(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    /* Getters & Setters */
    public void setTowerActionSpace(ActionSpace towerActionSpace)
    {
        this.towerActionSpace = towerActionSpace;
    }
    public void setFamilyMember(FamilyMember familyMember)
    {
        if( familyMember.isPlaced() )
        {
            //TODO: gestire (eccezione?)
        }
        else
            this.familyMember = familyMember;
    }

    /* Resetters */
    public void resetActionSpace()
    {
        this.towerActionSpace = null;
    }
    public void resetFamilyMember()
    {
        this.familyMember = null;
    }
}
