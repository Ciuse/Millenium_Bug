package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrVisualization;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewGame;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.controller.Controller;
import it.polimi.ingsw.ps31.messageMV.MVMessageVisitor;
import it.polimi.ingsw.ps31.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messageVC.VCVisitable;
import it.polimi.ingsw.ps31.model.StateModel.*;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

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
    private IntrVisualization stateInterpreterView;


    public View(PlayerId viewId, StateViewBoard stateViewBoard, List<StateViewPersonalBoard> stateViewPersonalBoardList, List<StateViewPlayer> stateViewPlayerList, StateViewGame stateViewGame) {
        this.viewId = viewId;
        this.stateViewBoard = stateViewBoard;
        this.stateViewPersonalBoardList = stateViewPersonalBoardList;
        this.stateViewPlayerList = stateViewPlayerList;
        this.stateViewGame = stateViewGame;
    }
    public void addController(Controller controller){
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
        message.accept(MVMessageVisitor);
    }


    public abstract void inserisciNome();

    public abstract void inserisciColore();

    public final void updateInfoPlayer(StateInfoPlayer stateInfoPlayer){
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if(viewPlayer.getPlayerId().equals(stateInfoPlayer.getPlayerId()))
            viewPlayer.updateState(stateInfoPlayer);

        }
    }

    public final void updatePlayerResources(StatePlayerResources statePlayerResources){
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if(viewPlayer.getPlayerId().equals(statePlayerResources.getPlayerId()))
            viewPlayer.updateState(statePlayerResources);

        }
    }
    public final void updateAllFamilyMember(StateAllFamilyMember stateAllFamilyMember){
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if(viewPlayer.getPlayerId().equals(stateAllFamilyMember.getIdFamilyMemberList()))
                viewPlayer.updateState(stateAllFamilyMember);

        }
    }

    public final void updateFamilyMember(StateFamilyMember stateFamilyMember){
        for (StateViewPlayer viewPlayer : stateViewPlayerList
                ) {
            if(viewPlayer.getPlayerId().equals(stateFamilyMember.getPlayerId()))
            viewPlayer.updateState(stateFamilyMember);

        }
    }

    public final void updatePersonalBoard(StatePersonalBoard statePersonalBoard){
        for (StateViewPersonalBoard viewPersonalBoard : stateViewPersonalBoardList
                ) {
            if(statePersonalBoard.getPlayerId().equals(viewPersonalBoard.getPlayerId()))
                viewPersonalBoard.updateState(statePersonalBoard);
        }
    }

    public final void updateCardBox(StateCardBox stateCardBox){
        for (StateViewPersonalBoard viewPersonalBoard : stateViewPersonalBoardList
                ) {
            if(viewPersonalBoard.getPlayerId().equals(stateCardBox.getPlayerId()))
                viewPersonalBoard.updateState(stateCardBox);
        }
    }
    public final void updateActionSpace(StateActionSpace stateActionSpace){
                stateViewBoard.updateState(stateActionSpace);
    }

    public final void updateTower(StateTower stateTower){
        stateViewBoard.updateState(stateTower);
    }

    public final void updateMarkerDisc(StateMarkerDisc stateMarkerDisc){
        stateViewBoard.updateState(stateMarkerDisc);
    }

    public final void updatePlayerAction(StatePlayerAction statePlayerAction){
         for (StateViewPlayer viewPlayer : stateViewPlayerList
                 ) {
             if(viewPlayer.getPlayerId().equals(statePlayerAction.getPlayerId()))
             viewPlayer.updateState(statePlayerAction);
         }
    }

    public final void updateGame(StateGame stateGame){
        stateViewGame.updateState(stateGame);
    }


    public abstract void askComand() throws IOException;

    public abstract void runTerminal() throws IOException;

    public abstract void printTitle();

    public abstract void printTower();

    public abstract void printMyPlayer();

    public abstract void printAllPlayer();

    public abstract void printMyPersonalBoard();

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
}