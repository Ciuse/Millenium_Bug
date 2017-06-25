package it.polimi.ingsw.ps31.controller;

import it.polimi.ingsw.ps31.messages.messageVC.VCMessageVisitor;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.board.Tower;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.card.Card;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameUtility;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.gameResource.Servant;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.PersonalBonusTiles;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.*;
import it.polimi.ingsw.ps31.server.VirtualView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class Controller implements Observer {
    private Model model;
    private ModelChoices modelChoices;
    private VirtualView virtualView;
    private LastModelStateForControl lastModelStateForControl;
    private TempModelStateForLeaderChoice tempModelStateForLeaderChoice;
    private GameUtility gameUtility;

    public void update(Observable o, Object arg) {
        VCMessageVisitor messageVisitor = new VCMessageVisitor();
        messageVisitor.setController(this);
        VCVisitable message = (VCVisitable) arg;
        message.accept(messageVisitor);
    }

    public void selectStartLeader(int leaderIdToCreate, PlayerId viewId) {
        boolean found = false;
        boolean found1 = false;
        for (int i = 0; i < tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().size(); i++) {
            if (viewId == tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().get(i).getPlayerId()) {
                for (Integer leaderId : tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().get(i).getLeaderId()
                        ) {
                    if (leaderId == leaderIdToCreate) {
                        found = true;
                        for (LeaderCard leaderCard : gameUtility.getTempLeaderCardList()
                                ) {
                            if (leaderIdToCreate == leaderCard.getLeaderId()) {
                                found1 = true;
                                for (Player player : gameUtility.getPlayerList()
                                        ) {
                                    if (player.getPlayerId().equals(tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().get(i).getPlayerId())) {
                                        player.addLeaderCard(leaderCard);
                                        gameUtility.getTempLeaderCardList().remove(leaderCard);
                                        modelChoices.incrementLeaderChoosenCounter();
                                    }
                                }
                            }
                        }
                        if (!found1) {
                            virtualView.reSendLastMessageToSpecificView("non ho trovato il leader tra i possibili leader rimasti in gioco", viewId);
                        }
                    }
                }
                if (!found) {
                    virtualView.reSendLastMessageToSpecificView("non ho trovato il leader tra le tue possibili scelte", viewId);
                }
            }
        }
    }

    public void selectStartPersonalBonusTiles(int numberOfChoice, PlayerId viewId) {
        boolean found = false;
        for (PersonalBonusTiles personalBonusTiles : gameUtility.getPersonalBonusTilesList()
                ) {
            if (numberOfChoice == personalBonusTiles.getPersonalBonusTilesId()) {
                found = true;
                modelChoices.setPersonalBonusTilesChosen(personalBonusTiles);
            }
        }
        if (!found)
            virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato l azione associata");
    }

    public void activeEffect(boolean isToActive, PlayerId viewId) {
        modelChoices.setActiveEffect(isToActive);
    }

    public void selectPlayerAction(String string, PlayerId viewId) {
        boolean legitAnswer = false;
        boolean found = false;
        StatePlayerAction statePlayerAction = (StatePlayerAction) lastModelStateForControl.getStateForControl();
        for (String actionName : statePlayerAction.getStringPlayerAction()
                ) {
            if (actionName.equals(string)) {
                legitAnswer = true;
            }
        }
        if (!legitAnswer) {
            virtualView.reSendLastMessage("(controller) Player mi hai mentito");
        } else {
            for (Action action : gameUtility.getPlayerInAction().getPlayerActionSet().getActionList()
                    ) {
                if (action.toString().equals(string)) {
                    modelChoices.setActionToDo(action);
                    found = true;
                }
            }
            if (!found)
                virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato l azione associata");
        }
    }

    public void selectActionSpace(int actionSpaceId, PlayerId viewId) {
        boolean found = false;
        ActionSpace actionSpaceToControl = null;
        if (actionSpaceId == 17) {
            actionSpaceToControl = gameUtility.getGameBoard().getCouncilPalace();
            found = true;
        }
        if (actionSpaceId == 18) {
            actionSpaceToControl = gameUtility.getGameBoard().getSmallHarvest();
            found = true;
        }
        if (actionSpaceId == 19) {
            actionSpaceToControl = gameUtility.getGameBoard().getBigHarvest();
            found = true;
        }
        if (actionSpaceId == 20) {
            actionSpaceToControl = gameUtility.getGameBoard().getSmallProduction();
            found = true;
        }
        if (actionSpaceId == 21) {
            actionSpaceToControl = gameUtility.getGameBoard().getBigProduction();
            found = true;
        }
        if (actionSpaceId >= 22 && actionSpaceId <= 25) {
            for (ActionSpace marketActionSpace : gameUtility.getGameBoard().getMarket().getActionSpaceList()
                    ) {
                if (actionSpaceId == marketActionSpace.getActionSpaceId()) {
                    actionSpaceToControl = marketActionSpace;
                    found = true;
                }
            }
        }
        if (found) {
            if (gameUtility.getPlayerInAction().getPlayerActionSet().getActionControlSet().occupiedActionSpaceControl(actionSpaceToControl)) {        //se esiste l action space controllo se posso meterci il famigliare
                modelChoices.setActionSpaceChosen(actionSpaceToControl);
            } else {
                virtualView.reSendLastMessage(gameUtility.getPlayerInAction().getActionControlSet().getOccupiedActionSpaceControl().getControlStringError());
            }
        } else {
            virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato l actionSpace");
        }
    }

    public void selectTowerCardSpace(CardColor towerColor, int floorNumber, PlayerId playerId) {
        boolean found = false;
        for (Tower tower : gameUtility.getGameBoard().getTowers()
                ) {
            if (tower.getColor().equals(towerColor)) {
                for (TowerCardSpace towerCardSpace : tower.getTowerCardSpaceList()
                        ) {
                    if (towerCardSpace.getTowerFloor() == floorNumber) {
                        if (towerCardSpace.getCard() != null) {     //controllo se vi è una carta
                            if (gameUtility.getPlayerInAction().getPlayerActionSet().getActionControlSet().occupiedActionSpaceControl(towerCardSpace.getActionSpace())) {     //controllo se l action space associato alla carta è già occupato
                                found = true;
                                modelChoices.setTowerCardSpaceChosen(towerCardSpace);
                            } else {
                                virtualView.reSendLastMessage("L'Action space associato alla carta è già occuapto");
                            }
                        } else {
                            virtualView.reSendLastMessage("Il tower Card Space selezionato non ha una carta");
                        }
                    }
                }
            }
        }
        if (!found)
            virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato l azione associata");
    }

    public void selectColor(PlayerColor playerColor, PlayerId playerId) {
        modelChoices.setPlayerColorChosen(playerColor);
    }

    public void selectFamilyMember(DiceColor familyMemberColor, PlayerId playerId) {
        boolean legitAnswer = false;
        for (FamilyMember familyMember : gameUtility.getPlayerInAction().getFamilyMembers()
                ) {
            if (familyMember.getDiceColor().equals(familyMemberColor)) {
                legitAnswer = true;
                if (gameUtility.getPlayerInAction().getPlayerActionSet().getActionControlSet().placedFamilyMemberControl(familyMember)) {       //controllo se il famigliare scelto dal giocatore era già stato piazzato
                    modelChoices.setFamilyMemberChosen(familyMember);
                } else {
                    virtualView.reSendLastMessage(gameUtility.getPlayerInAction().getActionControlSet().getPlacedFamilyMemberControl().getControlStringError());
                }
            }
        }
        if (!legitAnswer) {
            virtualView.reSendLastMessage("(controller) Player mi hai mentito");
        }
    }

    public void selectIfSupportTheChurch(boolean wannaSupport, PlayerId viewId) {
        modelChoices.setSupportTheChurch(wannaSupport);
    }

    public void selectLeaderToActivate(int leaderId, PlayerId viewId) {
        boolean found = false;
        for (LeaderCard leaderCard : gameUtility.getPlayerInAction().getLeaderCardList()
                ) {
            if (leaderCard.getLeaderId() == leaderId) {
                found = true;
                modelChoices.setLeaderCardChosen(leaderCard);
            }
        }
        if (!found)
            virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato il leader associata");
    }

    public void selectServantToPay(int servantToPay, PlayerId viewId) {
        List<Resource> servantsAsList = new ArrayList<>();
        Resource servantsAsResource = new Servant(servantToPay);
        servantsAsList.add(servantsAsResource);
        ResourceList servantsAsResourceList = new ResourceList(servantsAsList);

        if (gameUtility.getPlayerInAction().getPlayerActionSet().getActionControlSet().payResourceControl(servantsAsResourceList)) {
            modelChoices.setNumberOfServantsToPay(servantToPay);
        } else {
            virtualView.reSendLastMessage("(controller) Non hai risorse per pagare i servitori");
        }

    }

    public void selectListToPay(int listToPay, PlayerId viewId) {
        boolean found = false;
        List<ResourceList> resourceListToControl = lastModelStateForControl.getResourceListToControl();
        if (listToPay <= resourceListToControl.size()) {
            if (gameUtility.getPlayerInAction().getPlayerActionSet().getActionControlSet().payResourceControl(resourceListToControl.get(listToPay))) {
                found = true;
                modelChoices.setListToPay(listToPay);
            } else {
                virtualView.reSendLastMessage("(controller) Non hai abbastanza risorse per pagare l'effetto");
            }
        }
        if (!found)
            virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato la lista associata");
    }

    public void selectCouncilPrivilige(ResourceList resourceList, PlayerId viewId) {
        //TODO CREARE DA JSON I COUNCIL OF PRIVILEGE POSSIBILI
        List<ResourceList> resourceListToControl = lastModelStateForControl.getResourceListToControl();
        if (resourceList.equals(resourceListToControl)) {
            modelChoices.setResourceChosenFromPrivilege(resourceList);
        } else {
            virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato la lista di risorse con cui scambiare il privilegio");
        }
    }

}