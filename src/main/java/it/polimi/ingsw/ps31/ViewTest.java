package it.polimi.ingsw.ps31;

import it.polimi.ingsw.ps31.client.clientNetworking.ClientNetworkInterface;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewGame;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 28/06/2017.
 */
public class ViewTest {
    private final PlayerId viewId;
    private final StateViewBoard stateViewBoard;
    private final List<StateViewPlayer> stateViewPlayerList=new ArrayList<>();
    private final List<StateViewPersonalBoard> stateViewPersonalBoardList= new ArrayList<>();
    private final StateViewGame stateViewGame;
    private ClientNetworkInterface networkInterface;
    private boolean firstTime=true;


    public ViewTest(PlayerId viewId, int playerMaxNumber) {
        this.viewId = viewId;
        this.stateViewBoard = new StateViewBoard();
        this.stateViewGame = new StateViewGame(playerMaxNumber);
        PlayerId[] playerId=PlayerId.values();
        for (int i=0; i<playerMaxNumber; i++){
            this.stateViewPersonalBoardList.add(new StateViewPersonalBoard(playerId[i]));
            this.stateViewPlayerList.add(new StateViewPlayer(playerId[i]));
        }
    }

}
