package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateInfoPlayer;
import it.polimi.ingsw.ps31.model.StateModel.StatePlayerResources;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.PlayerResources;

/**
 * Created by giulia on 07/06/2017.
 */
public class StateViewPlayer {
    private PlayerId playerId;
    private String nickname;
    private PlayerColor playerColor;
    private PlayerResources playerResources;

    public PlayerId getPlayerId() {
        return playerId;
    }

    public String getNickname() {
        return nickname;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public PlayerResources getPlayerResources() {
        return playerResources;
    }

    public void updateState(StateInfoPlayer stateInfoPlayer){
        if(stateInfoPlayer.getPlayerId()!=null){
            this.playerId= stateInfoPlayer.getPlayerId();
        }
        if(stateInfoPlayer.getNickname()!=null){
            this.nickname= stateInfoPlayer.getNickname();
        }
        if(stateInfoPlayer.getPlayerColor()!=null){
            this.playerColor= stateInfoPlayer.getPlayerColor();
        }
    }

    public void updateState(StatePlayerResources statePlayerResources){
        if(statePlayerResources.getPlayerResources()!=null){
            this.playerResources=statePlayerResources.getPlayerResources();
        }
    }
}
