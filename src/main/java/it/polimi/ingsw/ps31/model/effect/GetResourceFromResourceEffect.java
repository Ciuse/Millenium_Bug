package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 18/05/2017.
 */
public class GetResourceFromResourceEffect extends GetResourceEffect { //per ogni risorsa di un tipo guadagno un'altra risorsa
    private final Resource requiredResource;
    public GetResourceFromResourceEffect(int cardId,Resource requiredResource,ResourceList resourceGained) {
        super(cardId,resourceGained);
        this.requiredResource = requiredResource;
    }

    @Override
    public void activate(Player player) {
        int factor = player.getPlayerResources().getSpecificResource(requiredResource.getClass()).getValue()%requiredResource.getValue();
        super.getResources().multiplyResourceList(factor);
        player.getPlayerActionSet().getTempResources(super.getResources());
    }

    public String getRequiredResourceString(){
        return requiredResource.toString();
    }

    public String getNameString(){
        return "ResFromRes";
    }
}
