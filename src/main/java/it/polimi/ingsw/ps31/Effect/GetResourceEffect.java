package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

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
