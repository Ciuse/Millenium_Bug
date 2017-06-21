package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 24/05/2017.
 */
public class OccupiedActionSpaceControl extends Control {
    private ActionSpace actionSpace = null;
    private List<Integer> defaultAllowedActionSpaces; //Lista delgi action space dove il giocatore può posizionarsi senza considerare il limite di player

    /* Constructor */
    public OccupiedActionSpaceControl(Player player) {
        super(player);
        this.defaultAllowedActionSpaces = new ArrayList<>();
    }

    @Override
    public String getControlStringError() {
        return "Non puoi mettere il tuo familyMember in questo actionSpace perchè è già occupato";
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
        //Ritorna true se l'action space è nella lista degli a.s. da non controllare
        if( defaultAllowedActionSpaces.contains(this.actionSpace.getActionSpaceId()) )
            return true;

        //Ritorna true se c'è almeno un posto libero nell'action space
        boolean ret = (actionSpace.getFamilyMemberList().size() < actionSpace.getFamilyMemberLimit());
        resetActionSpace();

        return ret;
    }

    /* Modifiers */
    public void addDefaultAllowedActionSpace (List<Integer> actionSpaceIdList)
    {
        this.defaultAllowedActionSpaces.addAll(actionSpaceIdList);
    }
}
