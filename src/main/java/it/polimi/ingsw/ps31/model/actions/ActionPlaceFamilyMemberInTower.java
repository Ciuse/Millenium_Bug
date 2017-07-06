package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceFamilyMember;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceTowerCardSpace;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionPlaceFamilyMemberInTower extends ActionPlaceFamilyMember {
    private TowerCardSpace towerCardSpace;
    private boolean immediateEffectsAreActivable = true;
    private boolean actionTimerEnded = false;

    public ActionPlaceFamilyMemberInTower(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    /* Getters & Setters */

    public TowerCardSpace getTowerCardSpace() {
        return towerCardSpace;
    }

    public void setTowerCardSpace(TowerCardSpace towerCardSpace) {
        this.towerCardSpace = towerCardSpace;
    }

    /* Resetters */
    public void resetTowerCardSpace() {
        this.towerCardSpace = null;
    }

    /* Class Methods */
    @Override
    public void activate() {
        boolean askAgain = true;

        player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), "Quale family member vuoi usare?", new ChoiceFamilyMember()));
        familyMember = player.getModel().getModelChoices().waitFamilyMemberChosen();
        player.setLastUsedFamilyMember(familyMember);

        if (familyMember != null) { //TIMER NON SCADUTO

            //chiedo se vuole pagare dei servitori
            player.getPlayerActionSet().payServants(super.familyMember);

            do {
                player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), "In quale tower card space vuoi mettere il tuo family member?", new ChoiceTowerCardSpace()));
                this.towerCardSpace = player.getModel().getModelChoices().waitTowerCardChosen();

                if (this.towerCardSpace != null) {      //TIMER NON SCADUTO

                    //Eseguo i controlli
                    if (actionControlSet.selfOccupiedTowerControl(familyMember, towerCardSpace.getTower())) {

                        if (actionControlSet.diceValueVsCardSpaceControl(familyMember.getTotalValue(), towerCardSpace)) {

                            if (actionControlSet.towerCostPlacementControl(towerCardSpace)) {

//                                if (actionControlSet.payCardControl(towerCardSpace.getCard(), null)) {

                                    if (actionControlSet.takeDevelopmentCardControl(towerCardSpace.getCard())) {

                                        //TODO SIMULARE L ATTIVAZIONE IN CATENA DEGLI EFFETI
                                        //pago la torre

                                        if (towerCardSpace.getTower().isOccupied()) {
                                            player.getPlayerActionSet().payTowerMoney();
                                        }
                                        //metto il famigliare
                                        towerCardSpace.getActionSpace().addFamilyMember(familyMember);
                                        askAgain = false;
                                        //controllo i parametri extra che settano le scomuniche
                                        if (immediateEffectsAreActivable)
                                            //attivo gli effetti immediati degli action space
                                            towerCardSpace.getActionSpace().activeEffectList(player);

                                        //pago la carta
                                        super.player.getPlayerActionSet().payCard();

                                        //prendo la carta
                                        super.player.addDevelopmentCard(this.towerCardSpace.takeCard());

                                        //metto la torre come occupata
                                        towerCardSpace.getTower().setOccupied(true);
                                    } else {      //TODO FARE OVERRIDE DEI GET CONTROL ERROR SPECIFICI
                                        player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getTakeDevelopmentCardControl().getControlStringError()));
                                    }
//                                } else {
//                                    player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getPayCardControl().getControlStringError()));
//                                }
                            } else {
                                player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getTowerCardCostPlacementControl().getControlStringError()));
                            }
                        } else {
                            player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getDiceValueActionSpaceControl().getControlStringError()));
                            askAgain = true;
                        }
                    } else {
                        player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getSelfOccupiedTowerControl().getControlStringError()));
                        askAgain = true;
                    }
                } else {
                    actionTimerEnded = true;
                    break;
                }

            } while (askAgain);

            if (!actionTimerEnded) {

                super.setUsed(true);
                player.getModel().notifyViews(new MVUpdateState("Aggiornato stato family member", familyMember.getStateFamilyMember()));
                player.getModel().notifyViews(new MVUpdateState("Aggiornato stato dell' action space nella tower", towerCardSpace.getActionSpace().getStateActionSpace()));

            } else
                player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));

        } else
            player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));

        resetTowerCardSpace();
        resetFamilyMember();
    }


    @Override
    public String toString() {
        return "Place FM in Tower";
    }


    /* Modifiers */
    public void setImmediateEffectsAreActivable(boolean areActivable)
    {
        this.immediateEffectsAreActivable = areActivable;
    }
}
