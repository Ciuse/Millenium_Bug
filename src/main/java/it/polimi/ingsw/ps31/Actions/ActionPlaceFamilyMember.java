package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.Board.ActionSpace;
import it.polimi.ingsw.ps31.Player.FamilyMember;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionPlaceFamilyMember extends Action {
    private ActionSpace actionSpace = null;
    private FamilyMember familyMember = null;

    public ActionPlaceFamilyMember(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
    }

    public void setActionSpace(ActionSpace actionSpace)
    {
        this.actionSpace = actionSpace;
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

    public void resetActionSpace()
    {
        this.actionSpace = null;
    }

    public void resetFamilyMember()
    {
        this.familyMember = null;
    }

    @Override
    public void activate()
    {
        if ( this.familyMember == null || this.actionSpace == null )
        {
            //TODO: gestire (eccezione?)
        }
        else
        {
            //TODO: va finita
            //checkTowerOccupation (3 gold)
            //checkSpaceOccupation
            //checkFamilyColor

            this.actionSpace.addFamilyMember(familyMember); //TODO: chi attiva gli effetti??
        }
    }
}
