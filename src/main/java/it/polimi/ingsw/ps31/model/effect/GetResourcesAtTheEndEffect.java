package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.gameThings.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 30/05/2017.
 */
public class GetResourcesAtTheEndEffect extends GetResourceEffect {

    public GetResourcesAtTheEndEffect(ResourceList resources) {
        super(resources);
    }
    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().addFinalVictoryPoints(super.getResources());
    }
}
