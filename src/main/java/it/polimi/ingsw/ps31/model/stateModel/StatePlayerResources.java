package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

/**
 * Created by giulia on 06/06/2017.
 */
public class StatePlayerResources extends StateType {
    private final PlayerId playerId;
    private final ResourceList playerResources;

    public StatePlayerResources(PlayerId playerId, ResourceList playerResources) {
        this.playerId = playerId;
        this.playerResources = playerResources;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public ResourceList getPlayerResources() {
        return playerResources;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
