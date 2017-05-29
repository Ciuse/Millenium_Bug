package it.polimi.ingsw.ps31.effect;

import it.polimi.ingsw.ps31.gameThings.ResourceList;
import it.polimi.ingsw.ps31.player.Player;

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
//        GetResources getResources = new GetResources(player);
//        getResources.setResourcesToGet(resources);
//        getResources.activate();
//TODO CAMBIARE IL NOME
    }
}
