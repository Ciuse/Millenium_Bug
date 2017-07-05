package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 24/05/2017.
 */
public class OccupiedActionSpaceControl extends Control {
    private ActionSpace actionSpace = null;
    private boolean canPlaceInAllOccupedActionSpace=false; //Ludovico Ariosto ti permette di ignorare questo controllo

    /* Constructor */
    public OccupiedActionSpaceControl(Player player) {
        super(player);
    }



    /* Getters & Setters */
    public void setActionSpace(ActionSpace actionSpace) {
        this.actionSpace = actionSpace;
    }

    @Override
    public String getControlStringError() {
        return "Non puoi mettere il tuo familyMember in questo actionSpace perchè è già occupato";
    }

    public void resetActionSpace() {
        this.actionSpace = null;
    }

    /* Class Methods */
    @Override
    public boolean execute() {
        if (this.actionSpace == null) {
            //TODO: gestire
            return false;
        }
        //Ritorna true se hai attivato il leader
        if (canPlaceInAllOccupedActionSpace) {
            resetActionSpace();
            return true;
        }


        //Ritorna true se c'è almeno un posto libero nell'action space
        if (actionSpace.getFamilyMemberLimit() != -1) {
            if (!actionSpace.getFamilyMemberList().isEmpty()) {
                if (actionSpace.getFamilyMemberList().size() < actionSpace.getFamilyMemberLimit()) {
                    resetActionSpace();
                    return true;
                } else {
                    resetActionSpace();
                    return false;
                }

            } else {
                resetActionSpace();
                return true;
            }
        }
        resetActionSpace();
        return true;
    }

    /* Modifiers */

    public void setCanPlaceInAllOccupedActionSpace(boolean canPlaceInAllOccupedActionSpace) {
        this.canPlaceInAllOccupedActionSpace = canPlaceInAllOccupedActionSpace;
    }
}
