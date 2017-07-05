package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

/**
 * Created by giulia on 09/06/2017.
 *
 * Stato che rappresenta le risorse del player
 *
 * @see StateType
 * @see MVUpdateState
 * @see it.polimi.ingsw.ps31.model.player.Player
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
