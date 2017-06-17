package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 26/05/2017.
 */
public class ActionPlaceFamilyMemberInBoard extends ActionPlaceFamilyMember {
    private ActionSpace actionSpace = null;

    /* Constructor */
    public ActionPlaceFamilyMemberInBoard(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    /* Getters & Setters */
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
             && actionControlSet.diceValueVsDiceColorControl(actionSpace.getDiceCost(), familyMember.getDiceColor()))
            {
                this.actionSpace.addFamilyMember(familyMember);
                super.player.setLastUsedFamilyMember(familyMember);
            }

            resetActionSpace();
            resetFamilyMember();
        }
    }
}
