package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 24/05/2017.
 */
public class OccupiedActionSpaceControl extends Control {
    private ActionSpace actionSpace = null;

    /* Constructor */
    public OccupiedActionSpaceControl(Player player) {
        super(player);
    }

    /* Getters & Setters */
    public ActionSpace getActionSpace() {
        return actionSpace;
    }

    public void setActionSpace(ActionSpace actionSpace) {
        this.actionSpace = actionSpace;
    }

    public void resetActionSpace() {
        this.actionSpace = null;
    }

    /* Class Methods */
    @Override
    public boolean execute() {
        if ( this.actionSpace == null )
        {
            //TODO: gestire
            return false;
        }
        //Ritorna true se c'è almeno un posto libero nell'action space
        boolean ret = (actionSpace.getFamilyMemberList().size() < actionSpace.getFamilyMemberLimit());
        resetActionSpace();

        return ret;
    }
}
