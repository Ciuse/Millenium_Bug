package it.polimi.ingsw.ps31.controller;

import it.polimi.ingsw.ps31.messages.messageVC.VCMessageVisitor;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.board.Tower;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
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
import it.polimi.ingsw.ps31.model.stateModel.LastModelStateForControl;
import it.polimi.ingsw.ps31.model.stateModel.PlayerPossibleChoice;
import it.polimi.ingsw.ps31.model.stateModel.StatePlayerAction;
import it.polimi.ingsw.ps31.model.stateModel.TempModelStateForLeaderChoice;
import it.polimi.ingsw.ps31.server.VirtualView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Giuseppe on 05/06/2017.
 *
 * Classe che rappresenta il controller del pattern MVC.
 * Il suo compito è quello di tradurre e verificare i messaggi provenienti dalle View, in
 * azioni che andranno a dare al Model le risposte di cui ha bisogno per poter proseguire
 * il suo corso.
 * Il controller verificherà anche che non vi sia alcun tipo di imbroglio da parte della view,
 * in quanto andrà a verificare sempre i dati ricevuto con i veri stati del model, quindi non ci sarà
 * modo di imbrogliare.
 * Inoltre in caso di errore da parte del messaggio inviato dalla view il controller dirà alla Virtual View
 * di reinviare l' ultimo messaggio con dentro la domanda da rifare al giocatore, e una stringa contentennte
 * il perchp il giocatore dovrà rieseguire l'azione.
 */
public class Controller extends Thread implements Observer {
    private Model model;
    private ModelChoices modelChoices;
    private VirtualView virtualView;
    private LastModelStateForControl lastModelStateForControl;
    private TempModelStateForLeaderChoice tempModelStateForLeaderChoice;
    private GameUtility gameUtility;


    public Controller(Model model, VirtualView virtualView, GameUtility gameUtility) {
        this.model = model;
        this.modelChoices=model.getModelChoices();
        this.lastModelStateForControl=model.getModelChoices().getLastModelStateForControl();
        this.tempModelStateForLeaderChoice=model.getModelChoices().getTempModelStateForLeaderChoice();
        this.virtualView = virtualView;
        this.gameUtility = gameUtility;
    }

    public void update(Observable o, Object arg) {
        VCMessageVisitor messageVisitor = new VCMessageVisitor();
        messageVisitor.setController(this);
        VCVisitable message = (VCVisitable) arg;
        message.accept(messageVisitor);
    }

    @Override
    public void run() {

    }

    /**
     * Metodo per gestire e controllare il draft iniziale dei leader di ogni player.
     * Verifica se il leader scelto di ogni player era tra le sue possibili scelte
     * e quindi se la view oltre ad aver risposto giusto non ha mentito
     *
     * @param leaderIdToCreate id del leader che la view ha mandato
     * @param viewId id del player che ha scelto
     */
    public void selectStartLeader(int leaderIdToCreate, PlayerId viewId) {
        boolean found = false;
        boolean found1 = false;
        boolean found2 = false;
        Player playerToAdd = null;
        LeaderCard leaderCardToAdd = null;
        for (PlayerPossibleChoice playerPossible : tempModelStateForLeaderChoice.getPlayerPossibleChoiceList()
                ) {
            if (viewId == playerPossible.getPlayerId()) {
                for (Integer leaderId : playerPossible.getLeaderId()
                        ) {
                    if (leaderId == leaderIdToCreate) {
                        found = true;
                        for (LeaderCard leaderCard : gameUtility.getLeaderCardList()
                                ) {
                            if (leaderIdToCreate == leaderCard.getLeaderId()) {
                                found1 = true;
                                for (Player player : gameUtility.getPlayerList()
                                        ) {
                                    if (player.getPlayerId().equals(playerPossible.getPlayerId())) {
                                        found2 = true;
                                        playerToAdd = player;
                                        leaderCard.setPlayerId(player.getPlayerId());
                                        leaderCardToAdd = leaderCard;
                                    }
                                }
                                if (found2) {
                                    playerToAdd.addLeaderCard(leaderCardToAdd);
                                    modelChoices.getTempModelStateForLeaderChoice().removerLeader(leaderCardToAdd);
                                    modelChoices.incrementLeaderChoosenCounter();
                                }
                            }

                        }
                    }
                }
            }
        }

        if (!found1) {
            virtualView.reSendLastMessageToSpecificView("non ho trovato il leader tra i possibili leader rimasti in gioco", viewId);
        }


        if (!found) {
            virtualView.reSendLastMessageToSpecificView("non ho trovato il leader tra le tue possibili scelte", viewId);
        }

    }

    /**
     * Metodo per gestire e controllare il draft iniziale dei personal tiles di ogni player.
     * Verifica se il tiles scelto di ogni player era tra le sue possibili scelte
     * e quindi se la view oltre ad aver risposto giusto non ha mentito
     *
     * @param tilesId id del tiles scelto
     * @param viewId id del player che ha scelto
     */
    public void selectStartPersonalBonusTiles(int tilesId, PlayerId viewId) {
        boolean found = false;
        for (PersonalBonusTiles personalBonusTiles : gameUtility.getPersonalBonusTilesList()
                ) {
            if (tilesId == personalBonusTiles.getPersonalBonusTilesId()) {
                found = true;
                if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                    modelChoices.setPersonalBonusTilesChosen(personalBonusTiles);
            }
        }
        if (!found)
            if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato l azione associata");
    }

    public void activeEffect(boolean isToActive, PlayerId viewId) {
        if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
            modelChoices.setActiveEffect(isToActive);
    }

    /**
     * verifica che l'azione scelta del player sia tra le possibili azioni che il player può
     * fare anche rispetto agli stati del model, e nel caso dice al model di proseguire sfruttando
     * l'informazione ricevuta dalla view.
     *
     * @param string nome dell' azione scelta
     * @param viewId id del player che ha scelto
     */
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
            if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                virtualView.reSendLastMessage("(controller) player mi hai mentito");
        } else {
            for (Action action : gameUtility.getPlayerInAction().getPlayerActionSet().getActionList()
                    ) {
                if (action.toString().equals(string)) {
                    found = true;
                    if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                        modelChoices.setActionToDo(action);
                }
            }
            if (!found)
                if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                    virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato l azione associata");
        }
    }

    /**
     * Verifica che l'action space scelto dalla view sia giusto, e che non sia occupato, e/o non
     * disponibile.
     *
     * @param actionSpaceId id action space scelto
     * @param viewId id player che ha scelto
     */
    public void selectActionSpace(int actionSpaceId, PlayerId viewId) {
        boolean found = false;
        boolean canPlace = true;
        ActionSpace actionSpaceToControl = null;
        if (actionSpaceId == 1) {
            actionSpaceToControl = gameUtility.getGameBoard().getCouncilPalace();
            found = true;
        }
        if (actionSpaceId == 2) {
            actionSpaceToControl = gameUtility.getGameBoard().getSmallHarvest();
            found = true;
            if (gameUtility.getGameBoard().getSmallHarvest().checkIfPlayerColor(gameUtility.getPlayerInAction().getPlayerColor())) {
                canPlace = false;
            }
            if(gameUtility.getGameBoard().getBigHarvest()!=null) {
                if (gameUtility.getGameBoard().getBigHarvest().checkIfPlayerColor(gameUtility.getPlayerInAction().getPlayerColor())) {
                    canPlace = false;
                }
            }
        }
        if (actionSpaceId == 3) {
            actionSpaceToControl = gameUtility.getGameBoard().getBigHarvest();
            found = true;
            if (gameUtility.getGameBoard().getSmallHarvest().checkIfPlayerColor(gameUtility.getPlayerInAction().getPlayerColor())) {
                canPlace = false;
            }
            if(gameUtility.getGameBoard().getBigHarvest()!=null) {

                if (gameUtility.getGameBoard().getBigHarvest().checkIfPlayerColor(gameUtility.getPlayerInAction().getPlayerColor())) {
                    canPlace = false;
                }
            }
        }
        if (actionSpaceId == 4) {
            actionSpaceToControl = gameUtility.getGameBoard().getSmallProduction();
            found = true;
            if (gameUtility.getGameBoard().getSmallProduction().checkIfPlayerColor(gameUtility.getPlayerInAction().getPlayerColor())) {
                canPlace = false;
            }
            if(gameUtility.getGameBoard().getBigProduction()!=null) {
                if (gameUtility.getGameBoard().getBigProduction().checkIfPlayerColor(gameUtility.getPlayerInAction().getPlayerColor())) {
                    canPlace = false;
                }
            }
        }
        if (actionSpaceId == 5) {
            actionSpaceToControl = gameUtility.getGameBoard().getBigProduction();
            found = true;
            if (gameUtility.getGameBoard().getSmallProduction().checkIfPlayerColor(gameUtility.getPlayerInAction().getPlayerColor())) {
                canPlace = false;
            }
            if(gameUtility.getGameBoard().getBigProduction()!=null) {
                if (gameUtility.getGameBoard().getBigProduction().checkIfPlayerColor(gameUtility.getPlayerInAction().getPlayerColor())) {
                    canPlace = false;
                }
            }
        }
        if (actionSpaceId >= 6 && actionSpaceId <= 9) {
            for (ActionSpace marketActionSpace : gameUtility.getGameBoard().getMarket().getActionSpaceList()
                    ) {
                if ((actionSpaceId + 16) == marketActionSpace.getActionSpaceId()) {
                    actionSpaceToControl = marketActionSpace;
                    found = true;
                }
            }
        }
        if (found) {
            if (gameUtility.getPlayerInAction().getPlayerActionSet().getActionControlSet().occupiedActionSpaceControl(actionSpaceToControl)) {        //se esiste l action space controllo se posso meterci il famigliare
                if (gameUtility.getPlayerInAction().getLastUsedFamilyMember().getDiceColor().equals(DiceColor.NEUTRAL)) {
                    canPlace = true;
                }
                if (canPlace) {
                    if (gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                        modelChoices.setActionSpaceChosen(actionSpaceToControl);
                }
                else  {
                    virtualView.reSendLastMessage("Hai già un famigliare colorato in produzione/raccolto ");

                }
            } else {
                if (gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                    virtualView.reSendLastMessage(gameUtility.getPlayerInAction().getActionControlSet().getOccupiedActionSpaceControl().getControlStringError());
            }
        } else {
            if (gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato l actionSpace");
        }
    }

    public void selectTowerCardSpace(CardColor towerColor, int floorNumber, PlayerId viewId) {
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
                                if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                                    modelChoices.setTowerCardSpaceChosen(towerCardSpace);
                            } else {
                                if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                                    virtualView.reSendLastMessage("L'Action space associato alla carta è già occuapto");
                            }
                        } else {
                            if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                                virtualView.reSendLastMessage("Il tower Card Space selezionato non ha una carta");
                        }
                    }
                }
            }
        }
        if (!found)
            if(floorNumber==0 && towerColor==null){
                if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId)){
                    modelChoices.setTowerCardSpaceChosen(new TowerCardSpace(null,null,null,0));
                }

            }
            else {
                if (gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                    virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato l azione associata");
            }
    }

    public void selectColor(PlayerColor playerColor, PlayerId viewId) {
        System.out.println("MESSAGGIO RICEVUTO");
        if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId)) {
            modelChoices.setPlayerColorChosen(playerColor);
        }
    }

    public void selectFamilyMember(DiceColor familyMemberColor, PlayerId viewId) {
        boolean legitAnswer = false;
        for (FamilyMember familyMember : gameUtility.getPlayerInAction().getFamilyMembers()
                ) {
            if (familyMember.getDiceColor().equals(familyMemberColor)) {
                legitAnswer = true;
                if (!gameUtility.getPlayerInAction().getPlayerActionSet().getActionControlSet().placedFamilyMemberControl(familyMember)) {       //controllo se il famigliare scelto dal giocatore era già stato piazzato
                    if (gameUtility.getPlayerInAction().getPlayerId().equals(viewId)) {
                        if (familyMemberColor.equals(DiceColor.NEUTRAL)) {
                            if (gameUtility.getPlayerInAction().getPlayerResources().getSpecificResource(Servant.class).getValue() > 0){
                                if (gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                                    modelChoices.setFamilyMemberChosen(familyMember);
                            }else{
                                virtualView.reSendLastMessage(gameUtility.getPlayerInAction().getActionControlSet().getPlacedFamilyMemberControl().getControlStringError(familyMember));
                            }
                        } else {
                            if (gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                                modelChoices.setFamilyMemberChosen(familyMember);
                        }
                    }
                } else {
                    if (gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                        virtualView.reSendLastMessage(gameUtility.getPlayerInAction().getActionControlSet().getPlacedFamilyMemberControl().getControlStringError(familyMember));
                }
            }
        }
        if (!legitAnswer) {
            if (gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                virtualView.reSendLastMessage("(controller) player mi hai mentito");
        }
    }

    public void selectIfSupportTheChurch(boolean wannaSupport, PlayerId viewId) {
        if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
            modelChoices.setSupportTheChurch(wannaSupport);
    }

    public void selectLeaderToActivate(int leaderId, PlayerId viewId) {
        boolean found = false;
        for (LeaderCard leaderCard : gameUtility.getPlayerInAction().getLeaderCardList()
                ) {
            if (leaderCard.getLeaderId() == leaderId) {
                found = true;
                if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                    modelChoices.setLeaderCardChosen(leaderCard);
            }
        }
        if (!found)
            if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato il leader associata");
    }

    public void selectLeaderToDiscard(int leaderId, PlayerId viewId) {
        boolean found = false;
        for (LeaderCard leaderCard : gameUtility.getPlayerInAction().getLeaderCardList()
                ) {
            if (leaderCard.getLeaderId() == leaderId) {
                found = true;
                if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                    modelChoices.setLeaderCardChosen(leaderCard);
            }
        }
        if (!found)
            if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato il leader associata");
    }

    public void selectLeaderToCopy(int leaderIdToReplace, int leaderIdToCopy,PlayerId viewId){
        boolean found = false;
        LeaderCard leaderToCopy=gameUtility.findOtherPlayerPlayedLeader(leaderIdToCopy,viewId);
        if(leaderToCopy!=null){
            found=true;
            LeaderCard newCopiedLeader=new LeaderCard("Lorenzo de' Medici",leaderIdToReplace,null,null,0,leaderToCopy.getAbilityOneTimeForTurn(),leaderToCopy.getPermanentAbility());
            if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                modelChoices.setLeaderCardChosen(newCopiedLeader);
        }
        if (!found)
            if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato il leader da copiare richiesto   ");
    }



    public void selectServantToPay(int servantToPay, PlayerId viewId) {
        List<Resource> servantsAsList = new ArrayList<>();
        Resource servantsAsResource = new Servant(servantToPay);
        servantsAsList.add(servantsAsResource);
        ResourceList servantsAsResourceList = new ResourceList(servantsAsList);

        if (gameUtility.getPlayerInAction().getPlayerActionSet().getActionControlSet().payResourceControl(servantsAsResourceList)) {
            if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                modelChoices.setNumberOfServantsToPay(servantToPay);
        } else {
            if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                virtualView.reSendLastMessage("(controller) Non hai risorse per pagare i servitori");
        }

    }

    public void selectListToPay(int listToPay, PlayerId viewId) {
        boolean found = false;
        List<ResourceList> resourceListToControl = lastModelStateForControl.getResourceListToControl();
        if (listToPay < resourceListToControl.size()) {
            found = true;
            if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                modelChoices.setListToPay(listToPay);
        }
        if (!found)
            if(gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato la lista associata");
    }

    public void selectCouncilPrivilige(ResourceList resourceList, PlayerId viewId) {
        boolean found = false;
        for (ResourceList resourceListToControl : gameUtility.getCouncilPrivilegeResChoice()
                ) {
            if (resourceList.equals(resourceListToControl)) {
                if (gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                    found = true;
                modelChoices.setResourceChosenFromPrivilege(resourceList);
            }
        }
        if (!found) {
            if (gameUtility.getPlayerInAction().getPlayerId().equals(viewId))
                virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato la lista di risorse con cui scambiare il privilegio");
        }
    }

}