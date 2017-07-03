package it.polimi.ingsw.ps31.model.actions;


import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceActionSpace;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceFamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 26/05/2017.
 */
public class ActionPlaceFamilyMemberInBoard extends ActionPlaceFamilyMember {
    private ActionSpace actionSpace = null;
    private boolean actionTimerEnded = false;

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
        boolean askAgain = true;

        player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), "Quale family member vuoi usare?", new ChoiceFamilyMember()));
        familyMember = player.getModel().getModelChoices().waitFamilyMemberChosen();
        player.setLastUsedFamilyMember(familyMember);

        if (familyMember != null) {     //TIMER NON SCADUTO

            player.getPlayerActionSet().payServants(familyMember); //richiamo l azione per pagare i family member

            do {
                player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), "In quale action space della board vuoi mettere il tuo family member?", new ChoiceActionSpace()));
                this.actionSpace = player.getModel().getModelChoices().waitActionSpaceChosen();

                if (this.actionSpace != null) {        //TIMER NON SCADUTO

                    //controllo i parametri extra dell azione settati dalle scomuniche
                    if (super.defaultDenyActionSpaces != null && super.defaultDenyActionSpaces.contains(actionSpace.getActionSpaceId())) { //TODO VERIFICARE STO CONTAIN CHE SENZA NULL MI DA ERRORE
                        player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, "Non puoi piazzare il family member qui perchè hai la scomunica"));
                    } else {

                        //Eseguo i controlli
                        if (actionControlSet.diceValueVsDiceColorControl(actionSpace.getDiceCost(), familyMember.getDiceColor())) {
                            this.actionSpace.addFamilyMember(familyMember);
                            //Attivo l'effetto dello spazio azione
                            this.actionSpace.activeEffectList(player);
                            super.player.setLastUsedFamilyMember(familyMember);
                            askAgain = false;
                        } else {
                            player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getDiceValueActionSpaceControl().getControlStringError()));
                            askAgain = true;
                        }
                    }
                } else {
                    actionTimerEnded = true;
                    break;
                }
            } while (askAgain);
            if (!actionTimerEnded) {
                super.setUsed(true);

                player.getModel().notifyViews(new MVUpdateState("Aggiornato stato family member", familyMember.getStateFamilyMember()));
                player.getModel().notifyViews(new MVUpdateState("Aggiornato stato dell' action space nella board", actionSpace.getStateActionSpace()));
            } else
                player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));

        } else
            player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));

        resetActionSpace();
        resetFamilyMember();
    }

    @Override
    public String toString() {
        return "Place FM Board";
    }
}
