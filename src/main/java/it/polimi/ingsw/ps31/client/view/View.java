package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewGame;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.controller.Controller;
import it.polimi.ingsw.ps31.messages.messageMV.MVMessageVisitor;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceActiveEffect;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceStartLeaderCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiseActionToDo;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.stateModel.*;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by giulia in 01/06/2017.
 */
public abstract class View extends Observable implements Observer {
    private final PlayerId viewId;
    private final StateViewBoard stateViewBoard;
    private final List<StateViewPlayer> stateViewPlayerList;
    private final List<StateViewPersonalBoard> stateViewPersonalBoardList;
    private final StateViewGame stateViewGame;


    public View(PlayerId viewId, StateViewBoard stateViewBoard, List<StateViewPersonalBoard> stateViewPersonalBoardList, List<StateViewPlayer> stateViewPlayerList, StateViewGame stateViewGame) {
        this.viewId = viewId;
        this.stateViewBoard = stateViewBoard;
        this.stateViewPersonalBoardList = stateViewPersonalBoardList;
        this.stateViewPlayerList = stateViewPlayerList;
        this.stateViewGame = stateViewGame;
    }

    public void addController(Controller controller) {
        this.addObserver(controller);
    }

    public void notifyController(VCVisitable message) {
        this.setChanged();
        notifyObservers(message);
    }


    @Override
    public void update(Observable o, Object arg) {
        MVMessageVisitor MVMessageVisitor = new MVMessageVisitor();
        MVMessageVisitor.setView(this);
        MVVisitable message = (MVVisitable) arg;

        if (message.isNotifyAll()) {          //se il messaggio riguarda tutti lo accetto
            message.accept(MVMessageVisitor);
        } else {
            if (message.getNotifySinglePlayer().equals(this.viewId)) {  //se il messaggio notifica solo un player controllo se è la mia la View di quel Player
                message.accept(MVMessageVisitor);
            }
        }

    }

    public abstract void askChoiceActiveEffect(ChoiceActiveEffect choiceActiveEffect);

    public abstract void askChoicePlayerAction(ChoiseActionToDo choiseActionToDo);

    public abstract void askChoiceStartLeader(ChoiceStartLeaderCard choiceStartLeaderCard);

    public abstract void askComand() throws IOException;


    public final void updateInfoPlayer(StateTypePlayer stateInfoPlayer) {
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if (viewPlayer.getPlayerId().equals(stateInfoPlayer.getPlayerId()))
                viewPlayer.updateState(stateInfoPlayer);
        }
        printAllPlayer();
    }

    public final void updatePlayerResources(StatePlayerResources statePlayerResources) {
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if (viewPlayer.getPlayerId().equals(statePlayerResources.getPlayerId()))
                viewPlayer.updateState(statePlayerResources);
        }
        printPlayerInAction();
    }

    public final void updateAllFamilyMember(StateAllFamilyMember stateAllFamilyMember) {
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if (viewPlayer.getPlayerId().equals(stateAllFamilyMember.getIdFamilyMemberList()))
                viewPlayer.updateState(stateAllFamilyMember);
        }
    }

    public final void updateFamilyMember(StateFamilyMember stateFamilyMember) {
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if (viewPlayer.getPlayerId().equals(stateFamilyMember.getPlayerId()))
                viewPlayer.updateState(stateFamilyMember);
        }
        printFamilyMemberInAction();
    }

    public final void updatePersonalBoard(StatePersonalBoard statePersonalBoard) {
        for (StateViewPersonalBoard viewPersonalBoard : stateViewPersonalBoardList
                ) {
            if (statePersonalBoard.getPlayerId().equals(viewPersonalBoard.getPlayerId()))
                viewPersonalBoard.updateState(statePersonalBoard);
        }
        printAllPersonalBoard();
    }

    public final void updateCardBox(StateCardBox stateCardBox) {
        for (StateViewPersonalBoard viewPersonalBoard : stateViewPersonalBoardList
                ) {
            if (viewPersonalBoard.getPlayerId().equals(stateCardBox.getPlayerId()))
                viewPersonalBoard.updateState(stateCardBox);
        }
        printPersonalBoardInAction();
    }

    public final void updateActionSpace(StateActionSpace stateActionSpace) {
        stateViewBoard.updateState(stateActionSpace);
        printBoardActionSpace();
    }

    public final void updateTower(StateTower stateTower) {
        stateViewBoard.updateState(stateTower);
        printTower();
    }

    public final void updateMarkerDisc(StateMarkerDisc stateMarkerDisc) {
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if (viewPlayer.getPlayerId().equals(stateMarkerDisc.getPlayerId())) {
                viewPlayer.updateState(stateMarkerDisc);
            }
        }
    }

    public final void updatePlayerAction(StatePlayerAction statePlayerAction) {
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if (viewPlayer.getPlayerId().equals(statePlayerAction.getPlayerId()))
                viewPlayer.updateState(statePlayerAction);
        }
        printPlayerAction();
    }

    public final void updateGame(StateGame stateGame) {
        stateViewGame.updateState(stateGame);
        printAllPlayer();
        printAllPersonalBoard();
    }

    public final void updateDevelopmentCard(StateDevelopmentCard stateDevelopmentCard) {
        stateViewGame.updateState(stateDevelopmentCard);
    }

    public final void updateLeaderCard(StateLeaderCard stateLeaderCard){
        for (StateViewPlayer stateViewPlayer:stateViewPlayerList
             ) {
           if(stateViewPlayer.getPlayerId().equals(stateLeaderCard.getPlayerId()))
               stateViewPlayer.updateState(stateLeaderCard);
        }
    }

    public abstract void runTerminal() throws IOException;

    public abstract void printTower();

    public abstract void printLastEvent(String string);

    public abstract void printPlayerInAction();

    public abstract void printAllPlayer();

    public abstract void printPlayerAction();

    public abstract void printPersonalBoardInAction();

    public abstract void printAllPersonalBoard();

    public abstract void printFamilyMemberInAction();

    public abstract void printBoardActionSpace();

    public abstract void printDevelopmentCard(int cardId);

    public abstract void printTextBox();

    public abstract void setCmdInterpreterView(CmdInterpreterView cmdInterpreterView);

    public PlayerId getViewId() {
        return viewId;
    }

    public StateViewBoard getStateViewBoard() {
        return stateViewBoard;
    }

    public List<StateViewPlayer> getStateViewPlayerList() {
        return stateViewPlayerList;
    }

    public List<StateViewPersonalBoard> getStateViewPersonalBoardList() {
        return stateViewPersonalBoardList;
    }

    public StateViewGame getStateViewGame() {
        return stateViewGame;
    }

    public StateViewPlayer getMyStateViewPlayer() {
        for (StateViewPlayer viewPlayer : getStateViewPlayerList()
                ) {
            if (viewPlayer.getPlayerId().equals(this.getViewId())) {
                return viewPlayer;
            }
        }
        return null;
    }

    public StateViewPersonalBoard getMyStateViewPersonalBoard() {
        for (StateViewPersonalBoard boardView : getStateViewPersonalBoardList()
                ) {
            if (boardView.getPlayerId().equals(this.getViewId())) {
                return boardView;
            }
        }
        return null;
    }
}