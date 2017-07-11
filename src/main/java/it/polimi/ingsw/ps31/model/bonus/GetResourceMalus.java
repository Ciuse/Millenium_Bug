package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 *
 * Malus che ti fa ottenere meno risorse ogni volta che si ottengono risorse del tipo indicato nell'effetto
 * L' effetto può avere una o due risorse a cui applciare il malus (in caso ce ne siano due viene richiesto
 * al giocatore quale delle due risorse non vuole ottenere
 *
 * @see it.polimi.ingsw.ps31.model.actions.ActionGetTempResourcesFromAllEffect
 */
public class GetResourceMalus extends Bonus{
    private final ResourceList resourceToSub1;
    private final ResourceList resourceToSub2;

    public GetResourceMalus(ResourceList resourceToSub1, ResourceList resourceToSub2) {
        super();
        this.resourceToSub1 = resourceToSub1;
        this.resourceToSub2 = resourceToSub2;
    }

    public ResourceList getResourceToSub1() {
        return resourceToSub1;
    }

    public ResourceList getResourceToSub2() {
        return resourceToSub2;
    }

    @Override
    public void activate(Player player) {
        if(resourceToSub1!=null){
            player.getPlayerActionSet().getGetTempResources().addResourceMalus(resourceToSub1);
        }
        if(resourceToSub2!=null){
            player.getPlayerActionSet().getGetTempResources().addResourceMalus(resourceToSub2);
        }
    }
}
