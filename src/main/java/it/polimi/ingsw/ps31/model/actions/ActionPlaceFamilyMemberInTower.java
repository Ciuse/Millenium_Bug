package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceFamilyMember;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceListToPay;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceTowerActionSpace;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionPlaceFamilyMemberInTower extends ActionPlaceFamilyMember {
    private TowerCardSpace towerCardSpace;
    private boolean immediateEffectsAreActivable = true;

    public ActionPlaceFamilyMemberInTower(Player player, ActionControlSet actionControlSet)
    {
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
    public void resetTowerCardSpace()
    {
        this.towerCardSpace = null;
    }

    /* Class Methods */
    @Override
    public void activate() {
        boolean askAgain = true;

        player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), "Quale family member vuoi usare?", new ChoiceFamilyMember()));
        this.familyMember = player.getModel().getModelChoices().waitFamilyMemberChosen();

        //chiedo se vuole pagare dei servitori
        player.getPlayerActionSet().payServants(super.familyMember);

        do {
            player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), "In quale tower action space vuoi mettere il tuo family member?", new ChoiceTowerActionSpace()));
            this.towerCardSpace = player.getModel().getModelChoices().waitTowerCardChosen();

            //Eseguo i controlli
            if (actionControlSet.selfOccupiedTowerControl(familyMember, towerCardSpace.getTower())) {
                if (actionControlSet.diceValueVsCardSpaceControl(familyMember.getTotalValue(), towerCardSpace)) {
                    if (actionControlSet.towerCostPlacementControl(towerCardSpace)) {
                        if(actionControlSet.takeDevelopmentCardControl(towerCardSpace.getCard())) {
                            //TODO SIMULARE L ATTIVAZIONE IN CATENA DEGLI EFFETI
                            //pago la torre
                            player.getPlayerActionSet().payTowerMoney();

                            //metto il famigliare
                            towerCardSpace.getActionSpace().addFamilyMember(familyMember);
                            player.setLastUsedFamilyMember(familyMember);
                            askAgain = false;
                            //controllo i parametri extra che settano le scomuniche
                            if (immediateEffectsAreActivable)
                                //attivo gli effetti immediati degli action space
                                towerCardSpace.getActionSpace().activeEffectList(player);

                            int listToPay = -1;
                            if (towerCardSpace.getCard().getCostList() != null) {
                                listToPay=0;
                                //se la carta ha almeno una lista da pagare vedo quante ne ha
                                if (towerCardSpace.getCard().getCostList().size() > 1) {     //se la carta ha più di una lista da pagare chiedo alla view quale vuole pagare
                                    do {
                                        String string = player.getPlayerId() + "Quale costo della carta vuoi pagare?";
                                        player.getModel().getModelChoices().getLastModelStateForControl().setResourceListToControl(towerCardSpace.getCard().getCostList());
                                        player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceListToPay(towerCardSpace.getCard().getCardId())));
                                        listToPay = player.getModel().getModelChoices().waitIntChosen();
                                    }while(!player.getPlayerResources().greaterThan(towerCardSpace.getCard().getCostList().get(listToPay))); // se fallisce il pagamento glielo richiedo magari poteva pagare solo i dei due costi
                                }
                            }
                            if (listToPay != -1) {
                                //pago la carta in base alla lista che mi ha detto il giocatore se c era piu di una lsita
                                player.getPlayerActionSet().payResources(towerCardSpace.getCard().getCostList().get(listToPay));
                            }
                            //prendo la carta
                            super.player.addDevelopmentCard(this.towerCardSpace.takeCard());
                            towerCardSpace.getTower().setOccupied(true);
                        }else{      //TODO FARE OVERRIDE DEI GET CONTROL ERROR SPECIFICI
                            player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getTakeDevelopmentCardControl().getControlStringError()));
                        }
                    } else{
                        player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getTowerCostPlacementControl().getControlStringError()));
                    }
                } else {
                    player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getDiceValueActionSpaceControl().getControlStringError()));
                    askAgain = true;
                }
            } else {
                player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getSelfOccupiedTowerControl().getControlStringError()));
                askAgain = true;
            }
        } while (askAgain);


        setUsed(true);
        resetTowerCardSpace();
        resetFamilyMember();
        player.getModel().notifyViews(new MVUpdateState("Aggiornato stato family member", familyMember.getStateFamilyMember()));
        player.getModel().notifyViews(new MVUpdateState("Aggiornato stato dell' action space nella tower", towerCardSpace.getActionSpace().getStateActionSpace()));
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
