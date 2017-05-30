package it.polimi.ingsw.ps31.actions;

import it.polimi.ingsw.ps31.board.ActionSpace;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Francesco on 26/05/2017.
 */
public class ActionPlaceFamilyMemberInBoard extends ActionPlaceFamilyMember {
    protected ActionSpace actionSpace = null;

    /* Constructor */
    public ActionPlaceFamilyMemberInBoard(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    public ActionSpace getActionSpace() {
        return actionSpace;
    }

    public void setActionSpace(ActionSpace actionSpace) {
        this.actionSpace = actionSpace;
    }

    /* Resetters */
    public void resetActionSpace()
    {
        this.actionSpace = null;
    }

    /* Class Methods */
    @Override
    public void activate()
    {
        //Controllo che i parametri siano settati
        if ( this.familyMember == null || this.actionSpace == null )
        {
            //TODO: gestire (eccezione?)
        } else
        {
            if (actionControlSet.placedFamilyMemberControl(familyMember)
             && actionControlSet.occupiedActionSpaceControl(actionSpace)
             && actionControlSet.diceValueVsDiceColorControl(actionSpace.getDiceCost(), familyMember.getDice().getColor()))
            {
                this.actionSpace.addFamilyMember(familyMember);
                super.player.setLastUsedFamilyMember(familyMember);
            }

        }
    }
}
