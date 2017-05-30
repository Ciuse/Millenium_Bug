package it.polimi.ingsw.ps31.effect;

import it.polimi.ingsw.ps31.gameThings.ResourceList;
import it.polimi.ingsw.ps31.player.Player;

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
