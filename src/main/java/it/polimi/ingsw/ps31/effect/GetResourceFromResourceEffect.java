package it.polimi.ingsw.ps31.effect;

import it.polimi.ingsw.ps31.gameThings.Resource;
import it.polimi.ingsw.ps31.gameThings.ResourceList;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by giulia on 18/05/2017.
 */
public class GetResourceFromResourceEffect extends GetResourceEffect {
    private final Resource requiredResource;
    public GetResourceFromResourceEffect(ResourceList resourceGained, Resource requiredResource) {
        super(resourceGained);
        this.requiredResource = requiredResource;
    }

    @Override
    public void activate(Player player) {
        //TODO modificare appena fra ha creato la risorsa di liste nel player.
        int factor = player.getPlayerResources().getPlayerResourceAsResourceList().getSpecificResource(requiredResource.getClass()).getValue()%requiredResource.getValue();
        super.getResources().multiplyResourceList(factor);
        player.getPlayerActionSet().getResources(super.getResources());
    }

}
