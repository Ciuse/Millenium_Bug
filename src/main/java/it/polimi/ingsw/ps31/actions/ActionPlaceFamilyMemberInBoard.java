package it.polimi.ingsw.ps31.actions;

import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Francesco on 26/05/2017.
 */
public class ActionPlaceFamilyMemberInBoard extends ActionPlaceFamilyMember {

    /* Constructor */
    public ActionPlaceFamilyMemberInBoard(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    /* Class Methods */
    @Override
    public void activate()
    {
        //Controllo che i parametri siano settati
        if ( this.familyMember == null || this.towerActionSpace == null )
        {
            //TODO: gestire (eccezione?)
        } else
        {
            if (actionControlSet.placedFamilyMemberControl(familyMember)
             && actionControlSet.occupiedActionSpaceControl(towerActionSpace)
             && actionControlSet.diceValueVsDiceColorControl(towerActionSpace.getDiceCost(), familyMember.getDice().getColor()))
            {
                this.towerActionSpace.addFamilyMember(familyMember);
                player.setLastUsedFamilyMember(familyMember);
            }

        }
    }
}
