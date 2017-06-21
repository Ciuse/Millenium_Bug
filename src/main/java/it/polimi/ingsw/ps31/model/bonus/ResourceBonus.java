package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class ResourceBonus extends Bonus{
    private final Resource resourceToSub1;
    private final Resource resourceToSub2;

    protected ResourceBonus(Resource resourceToSub1, Resource resourceToSub2) {
        super();
        this.resourceToSub1 = resourceToSub1;
        this.resourceToSub2 = resourceToSub2;
    }

    public Resource getResourceToSub1() {
        return resourceToSub1;
    }

    public Resource getResourceToSub2() {
        return resourceToSub2;
    }

    @Override
    public void activate(Player player) {

    }
}
