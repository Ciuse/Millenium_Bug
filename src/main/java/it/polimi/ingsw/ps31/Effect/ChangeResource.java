package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.GameThings.ResourceList;

/**
 * Created by giulia on 17/05/2017.
 */
public class ChangeResource extends GetResource {
    private final ResourceList resourceToPay; // la carta ha delle risorse da pagare per poter ottenere nuove risorse
    private final ResourceList resourceToPay2; //alcune carte hanno una doppia scelta sulle risorse da pagare per ottenere nuove risorse

    public ChangeResource(ResourceList resourceToPay,ResourceList resourceToPay2, ResourceList resourceGained) {
        super(resourceGained);
        this.resourceToPay = resourceToPay;
        this.resourceToPay2 = resourceToPay2;
    }
}
