package it.polimi.ingsw.ps31.controller;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.messages.messageVC.VCMessageVisitor;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiseActionToDo;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.game.GameUtility;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.LastModelStateForControl;
import it.polimi.ingsw.ps31.model.stateModel.StatePlayerAction;
import it.polimi.ingsw.ps31.model.stateModel.TempModelStateForLeaderChoice;
import it.polimi.ingsw.ps31.server.VirtualView;

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
        StatePlayerAction statePlayerAction = (StatePlayerAction) lastModelStateForControl.getStateForControl();
        for (String actionName : statePlayerAction.getStringPlayerAction()
                ) {
            if (actionName.equals(string)) {
                legitAnswer = true;
            }
        }
        if (!legitAnswer) {
            virtualView.reSendLastMessage("Player mi hai mentito");
        } else {
            final String string1 = gameUtility.getPlayerInAction().getPlayerActionSet().getActiveEndButton().toString();
            String string2 = gameUtility.getPlayerInAction().getPlayerActionSet().getPlaceFamilyMemberInBoard().toString();
            String string3 = gameUtility.getPlayerInAction().getPlayerActionSet().getPlaceFamilyMemberInTower().toString();
            String string4 = gameUtility.getPlayerInAction().getPlayerActionSet().getActiveLeaderCard().toString();
            String string5= gameUtility.getPlayerInAction().getPlayerActionSet().getDiscardLeaderCard().toString();

            if (string1.equals(string)) {
                gameUtility.getPlayerInAction().getPlayerActionSet().activeEndButton();
            }
            if(string2.equals(string)){
                gameUtility.getPlayerInAction().getPlayerActionSet().placeFamilyMemberInBoard();
            }
            if(string3.equals(string)){
                gameUtility.getPlayerInAction().getPlayerActionSet().placeFamilyMemberInTower();
            }
            if(string4.equals(string)){
                gameUtility.getPlayerInAction().getPlayerActionSet().activeLeaderCard();
            }
            if(string5.equals(string)){
                gameUtility.getPlayerInAction().getPlayerActionSet().discardLeaderCard();
            }
            virtualView.reSendLastMessage("Mi dispiace non ho trovato l azione associata");
        }
    }

}