package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 18/05/2017.
 */
public class GetResourceFromResourceEffect extends GetResourceEffect { //per ogni risorsa di un tipo guadagno un'altra risorsa
    private final Resource requiredResource;
    public GetResourceFromResourceEffect(ResourceList resourceGained, Resource requiredResource) {
        super(resourceGained);
        this.requiredResource = requiredResource;
    }

    @Override
    public void activate(Player player) {
        //TODO modificare appena fra ha creato la risorsa di liste nel player.
        int factor = player.getPlayerResources().getPlayerResourceList().getSpecificResource(requiredResource.getClass()).getValue()%requiredResource.getValue();
        super.getResources().multiplyResourceList(factor);
        player.getPlayerActionSet().getResources(super.getResources());
    }

    public String requiredResourceString(){
        return requiredResource.toString();
    }

    public String nameString(){
        return "ResFromRes";
    }
}
