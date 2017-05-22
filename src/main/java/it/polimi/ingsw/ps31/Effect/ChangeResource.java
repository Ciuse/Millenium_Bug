package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.GameThings.ResourceList;

import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
public class ChangeResource extends GetResource {
    private final List<ResourceList> resourceToPayList; // la carta ha delle risorse da pagare per poter ottenere nuove risorse
     //alcune carte hanno una doppia scelta sulle risorse da pagare per ottenere nuove risorse

    public ChangeResource(List<ResourceList> resourceToPayList,ResourceList resourceGained) {
        super(resourceGained);
        this.resourceToPayList = resourceToPayList;
    }
    //TODO implementare un metodo che una volta che riceve dall'utente una scelta l'elemento della lista da pagare sarà l'elemento da lui scelto
}
