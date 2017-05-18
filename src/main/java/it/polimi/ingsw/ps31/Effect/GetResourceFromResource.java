package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.GameThings.ResourceList;

/**
 * Created by giulia on 18/05/2017.
 */
public class GetResourceFromResource extends GetResource {
    private final ResourceList requiredResource;
    public GetResourceFromResource(ResourceList resourceGained, ResourceList requiredResource) {
        super(resourceGained);
        this.requiredResource = requiredResource;
    }



}
