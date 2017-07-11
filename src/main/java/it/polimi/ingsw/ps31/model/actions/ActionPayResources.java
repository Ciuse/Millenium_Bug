package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 23/05/2017.
 *
 * Azione per poter pagare una lista di risorse.
 * Necessita di una lista di risorse
 */
public class ActionPayResources extends Action {
    private ResourceList resourceToPay = null;

    /* Constructor */
    public ActionPayResources(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    /* Setter & Getter */
    public void setResourceToPay(ResourceList resourceToPay) {
        this.resourceToPay = resourceToPay;
    }

    public void resetResourceToPay() {
        this.resourceToPay = null;
    }

    /**
     * sottraggo al player le risorse
     */
    @Override
    public void activate() {

        //Eseguo il controllo
        if (super.actionControlSet.payResourceControl(this.resourceToPay)) {
            //Eseguo l'azione
            for (Resource currentResource : resourceToPay.getListOfResource())
                player.subResources(currentResource);
        }

        resetResourceToPay();

    }

}
