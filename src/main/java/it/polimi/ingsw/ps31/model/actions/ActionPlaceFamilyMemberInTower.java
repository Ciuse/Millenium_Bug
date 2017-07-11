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
 *
 * Azione di piazzamento di un famigliare nella torre (verrà chiesto al giocatore di scegliere lo spazio contenente
 * la carta e non l'action space associato)
 *
 * @see it.polimi.ingsw.ps31.model.actionControls.OccupiedActionSpaceControl
 * @see it.polimi.ingsw.ps31.model.actionControls.TowerCardCostPlacementControl
 * @see it.polimi.ingsw.ps31.model.actionControls.DiceValueCardSpaceControl
 * @see it.polimi.ingsw.ps31.model.actionControls.TakeDevelopmentCardControl
 */
public class ActionPlaceFamilyMemberInTower extends ActionPlaceFamilyMember {
    private TowerCardSpace towerCardSpace;
    private boolean immediateEffectsAreActivable = true;
    private boolean actionTimerEnded = false;
    private boolean exitFromAction =false;

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

    /**
     * Viene chiesto al giocatore quale famigliare usare e successivamente dopo aver anche chiesto di pagare servitori
     * viene chiesto dove vuole posizionarlo, successivamente verranno eseguiti i controlli in merito.
     * Verrà pagata la torre, la carta, e attivati gli effetti degli action space se presenti, oltrea ad aggiungere
     * la carta a quelle del player e attivarne gli effetti
     */
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
                    if (this.towerCardSpace.getActionSpace() == null && towerCardSpace.getTower() == null && towerCardSpace.getTowerFloor() == 0) {
                        exitFromAction = true;
                        break;
                    }
                    //Eseguo i controlli
                    if (actionControlSet.selfOccupiedTowerControl(familyMember, towerCardSpace.getTower())) {

                        if (actionControlSet.diceValueVsCardSpaceControl(familyMember.getTotalValue(), towerCardSpace)) {

                            if (actionControlSet.towerCostPlacementControl(towerCardSpace)) {

                                if (actionControlSet.takeDevelopmentCardControl(towerCardSpace.getCard())) {

                                    if (towerCardSpace.getTower().isOccupied()) {
                                        //pago la torre
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
                                } else {
                                    player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getTakeDevelopmentCardControl().getControlStringError()));
                                }
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
            if (!exitFromAction) {
                if (!actionTimerEnded) {

                    super.setUsed(true);
                    player.getModel().notifyViews(new MVUpdateState("Aggiornato stato family member", familyMember.getStateFamilyMember()));
                    player.getModel().notifyViews(new MVUpdateState("Aggiornato stato dell' action space nella tower", towerCardSpace.getActionSpace().getStateActionSpace()));

                } else
                    player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));
            } else
                player.getModel().notifyViews(new MVStringToPrint(null, true, "Premuto Esc -> Azione annullata"));

        } else
            player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));

        actionTimerEnded=false;
        exitFromAction=false;
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
