package it.polimi.ingsw.ps31.controller;

import it.polimi.ingsw.ps31.messages.messageVC.VCMessageVisitor;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameUtility;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.LastModelStateForControl;
import it.polimi.ingsw.ps31.model.stateModel.StatePlayerAction;
import it.polimi.ingsw.ps31.model.stateModel.TempModelStateForLeaderChoice;
import it.polimi.ingsw.ps31.server.VirtualView;

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

    public void createLeader(int leaderIdToCreate, PlayerId viewId) {
        for (int i = 0; i < tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().size(); i++) {
            if (viewId == tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().get(i).getPlayerId()) {
                for (Integer leaderId : tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().get(i).getLeaderId()
                        ) {
                    if (leaderId == leaderIdToCreate) {
                        for (LeaderCard leaderCard : gameUtility.getTempLeaderCardList()
                                ) {
                            if (leaderIdToCreate == leaderCard.getLeaderId()) {
                                for (Player player : gameUtility.getPlayerList()
                                        ) {
                                    if (player.getPlayerId().equals(tempModelStateForLeaderChoice.getPlayerPossibleChoiceList().get(i).getPlayerId())) {
                                        player.addLeaderCard(leaderCard);
                                        gameUtility.getTempLeaderCardList().remove(leaderCard);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    public void activeEffect(boolean isToActive, PlayerId viewId) {
        modelChoices.setActiveEffect(isToActive);
    }

    public void doPlayerAction(String string, PlayerId viewId) {
        boolean legitAnswer = false;
        boolean found=false;
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
            for (Action action: gameUtility.getPlayerInAction().getPlayerActionSet().getActionList()
                    ) {
                if(action.toString().equals(string)){
                    modelChoices.setActionToDo(action);
                    found=true;
                }
            }
            if(!found)
            virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato l azione associata");
        }
    }

    public void selectActionSpace(int actionSpaceId, PlayerId viewId){
        boolean legitAnswer = true;
        boolean found=false;
        if(gameUtility.getPlayerMaxNumber()<=3){
            if(actionSpaceId==24||actionSpaceId==25){           //controllo se non mi ha inserito action space coperti
                legitAnswer=false;
            }
            if(gameUtility.getPlayerMaxNumber()<=2&&(actionSpaceId==19 ||actionSpaceId==21)){       //controllo se non mi ha inserito action space coperti
                legitAnswer=false;
            }
        }
        if (!legitAnswer) {
            virtualView.reSendLastMessage("(controller) Player mi hai mentito");
        }else {
            if(actionSpaceId==17){
                modelChoices.setActionSpaceChosen(gameUtility.getGameBoard().getCouncilPalace());
                found=true;
            }
            if(actionSpaceId==18){
                modelChoices.setActionSpaceChosen(gameUtility.getGameBoard().getSmallHarvest());
                found=true;
            }
            if(actionSpaceId==19){
                modelChoices.setActionSpaceChosen(gameUtility.getGameBoard().getBigHarvest());
                found=true;
            }
            if(actionSpaceId==20){
                modelChoices.setActionSpaceChosen(gameUtility.getGameBoard().getSmallProduction());
                found=true;
            }
            if(actionSpaceId==21){
                modelChoices.setActionSpaceChosen(gameUtility.getGameBoard().getBigProduction());
                found=true;
            }
            if(actionSpaceId>=22&&actionSpaceId<=25){
                for (ActionSpace actionSpace : gameUtility.getGameBoard().getMarket().getActionSpaceList()
                        ) {
                    if(actionSpaceId==actionSpace.getActionSpaceId()){
                        modelChoices.setActionSpaceChosen(actionSpace);
                        found=true;
                    }
                }
            }
            if(!found)
                virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato l actionSpace");
        }
    }

    public void selectColor(PlayerColor playerColor, PlayerId playerId){
        modelChoices.setPlayerColorChosen(playerColor);
    }

    public void selectFamilyMember(DiceColor familyMemberColor, PlayerId playerId){
        boolean legitAnswer = true;
        boolean found=false;
        for (FamilyMember familyMember: gameUtility.getPlayerInAction().getFamilyMembers()
             ) {
            if (familyMember.getDiceColor().equals(familyMemberColor)) {
                if (familyMember.isPlaced()) {
                    legitAnswer = false;
                }
                else {
                    modelChoices.setFamilyMemberChosen(familyMember);
                    found=true;
                }
            }
        }
        if (!legitAnswer) {
            virtualView.reSendLastMessage("(controller) Player mi hai mentito");
        }
        if(!found)
            virtualView.reSendLastMessage("(controller) Mi dispiace non ho trovato il family member da piazzare");
    }

}