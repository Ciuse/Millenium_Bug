package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.player.PlayerResources;

/**
 * Created by giulia on 06/06/2017.
 */
public class StateResources extends StateInfo{
    private final PlayerResources playerResources;

    public StateResources(PlayerResources playerResources) {
        this.playerResources = playerResources;
    }

    public PlayerResources getPlayerResources() {
        return playerResources;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
