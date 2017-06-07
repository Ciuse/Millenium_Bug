package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 06/06/2017.
 */
public class StateInfoPlayer extends StateInfo {
    private final PlayerId playerId;
    private final String nickname;
    private final PlayerColor playerColor;

    public StateInfoPlayer(PlayerId playerId, String nickname, PlayerColor playerColor) {
        this.playerId = playerId;
        this.nickname = nickname;
        this.playerColor = playerColor;

    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public String getNickname() {
        return nickname;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
