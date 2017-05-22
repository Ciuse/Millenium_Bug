package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by giulia on 15/05/2017.
 */
public class GetResource extends Effect {
    private final ResourceList resources;

    public GetResource(ResourceList resources){
        this.resources=resources;
    }



    @Override
    public void activate(Player player) {

    }
}
