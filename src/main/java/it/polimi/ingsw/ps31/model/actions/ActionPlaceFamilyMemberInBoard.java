package it.polimi.ingsw.ps31.model.actions;


import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceActionSpace;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceFamilyMember;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceTowerCardSpace;
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
    public void activate() {
        super.notifyViews(new MVAskChoice(player.getPlayerId(),"Quale family member vuoi usare?",new ChoiceFamilyMember()));
        this.familyMember=super.waitFamilyMemberChosen();
        super.notifyViews(new MVAskChoice(player.getPlayerId(),"In quale action space della board vuoi mettere il tuo family member?",new ChoiceActionSpace()));
        this.actionSpace=super.waitActionSpaceChosen();
        //Controllo che i parametri siano settati
        if (this.familyMember == null || this.actionSpace == null) {
            //TODO: gestire (eccezione?)
        } else if (super.defaultDenyActionSpaces.contains(actionSpace.getActionSpaceId())) {
            super.notifyViews(new MVStringToPrint(player.getPlayerId(), false, "Non puoi piazzare il family member perchè hai la scomunica"));
            return;
        }
        else {
            if (actionControlSet.placedFamilyMemberControl(familyMember)) {
                if (actionControlSet.occupiedActionSpaceControl(actionSpace)) {
                    if (actionControlSet.diceValueVsDiceColorControl(actionSpace.getDiceCost(), familyMember.getDiceColor())) {
                        this.actionSpace.addFamilyMember(familyMember);
                        super.player.setLastUsedFamilyMember(familyMember);
                    } else
                        super.notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getPlacedFamilyMemberControl().getControlStringError()));
                } else
                    super.notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getOccupiedActionSpaceControl().getControlStringError()));
            }  else
                super.notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getDiceValueVsDiceColorControl().getControlStringError()));
            super.notifyViews(new MVUpdateState("Aggiornato stato family member",familyMember.getStateFamilyMember()));
            super.notifyViews(new MVUpdateState("Aggiornato stato dell' action space nella board",actionSpace.getStateActionSpace()));
        }

        super.setUsed(true);
        resetActionSpace();
        resetFamilyMember();
    }

    @Override
    public String toString() {
        return "Place FM Board";
    }
}
