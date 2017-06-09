package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.view.stateView.*;
import it.polimi.ingsw.ps31.model.StateModel.*;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.message.MexVisitable;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;

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


    public View(PlayerId viewId, StateViewBoard stateViewBoard, List<StateViewPersonalBoard> stateViewPersonalBoardList, List<StateViewPlayer> stateViewPlayerList) {
        this.viewId = viewId;
        this.stateViewBoard = stateViewBoard;
        this.stateViewPersonalBoardList = stateViewPersonalBoardList;
        this.stateViewPlayerList = stateViewPlayerList;
    }

    @Override
    public void update(Observable o, Object arg) {
        MessageVisitor messageVisitor = new MessageVisitor();
        MexVisitable message = (MexVisitable) arg;
        message.accept(messageVisitor);
    }


    public abstract String inserisciNome();

    public abstract PlayerColor inserisciColore();

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



}