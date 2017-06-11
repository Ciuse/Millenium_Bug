package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.CmdInterpreterView;
import it.polimi.ingsw.ps31.client.view.interpreterOfCommand.IntrVisualization;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.model.StateModel.StateAllFamilyMember;
import it.polimi.ingsw.ps31.model.StateModel.StateFamilyMember;
import it.polimi.ingsw.ps31.model.StateModel.StateInfoPlayer;
import it.polimi.ingsw.ps31.model.StateModel.StatePlayerResources;
import it.polimi.ingsw.ps31.model.StateModel.*;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.message.MexVisitable;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by giulia in 01/06/2017.
 */
public abstract class View implements Observer {
    private final PlayerId viewId;
    private final StateViewBoard stateViewBoard;
    private final List<StateViewPlayer> stateViewPlayerList;
    private final List<StateViewPersonalBoard> stateViewPersonalBoardList;
    private IntrVisualization stateInterpreterView;


    public View(PlayerId viewId, StateViewBoard stateViewBoard, List<StateViewPersonalBoard> stateViewPersonalBoardList, List<StateViewPlayer> stateViewPlayerList) {
        this.viewId = viewId;
        this.stateViewBoard = stateViewBoard;
        this.stateViewPersonalBoardList = stateViewPersonalBoardList;
        this.stateViewPlayerList = stateViewPlayerList;
    }

    @Override
    public void update(Observable o, Object arg) {
        MessageVisitor messageVisitor = new MessageVisitor();
        messageVisitor.setView(this);
        MexVisitable message = (MexVisitable) arg;
        message.accept(messageVisitor);
    }


    public abstract void inserisciNome();

    public abstract void inserisciColore() throws IOException;

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

    public abstract void askComand() throws IOException;

    public abstract void runTerminal() throws IOException;

    public abstract void setCmdInterpreterView(CmdInterpreterView cmdInterpreterView);

}