package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.PlayerResources;

/**
 * Created by giulia on 06/06/2017.
 */
public class StatePlayerResources extends StateType {
    private final PlayerId playerId;
    private final PlayerResources playerResources;

    public StatePlayerResources(PlayerId playerId, PlayerResources playerResources) {
        this.playerId = playerId;
        this.playerResources = playerResources;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public PlayerResources getPlayerResources() {
        return playerResources;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
