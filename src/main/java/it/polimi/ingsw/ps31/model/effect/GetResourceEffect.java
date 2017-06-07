package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 15/05/2017.
 */
public class GetResourceEffect extends Effect {

    private final ResourceList resources;

    public GetResourceEffect(ResourceList resources){
        this.resources=resources;
    }

    public ResourceList getResources(){
        return this.resources;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getResources(this.resources);
    }
}
