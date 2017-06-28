package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
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
