package it.polimi.ingsw.ps31.effect;

import it.polimi.ingsw.ps31.gameThings.ResourceList;

/**
 * Created by giulia on 18/05/2017.
 */
public class GetResourceFromResourceEffect extends GetResourceEffect {
    private final ResourceList requiredResource;
    public GetResourceFromResourceEffect(ResourceList resourceGained, ResourceList requiredResource) {
        super(resourceGained);
        this.requiredResource = requiredResource;
    }



}
