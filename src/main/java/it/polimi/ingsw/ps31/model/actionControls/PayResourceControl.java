package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 24/05/2017.
 *
 * Controllo sulla passibilità di pagare una ResourceList specifica
 */
public class PayResourceControl extends Control {
    private ResourceList resourceList = null;


    /* Constructor*/
    public PayResourceControl(Player player) {
        super(player);
    }


    /* Setters & Getters */
    public ResourceList getResourceList() {
        return resourceList;
    }

    @Override
    public String getControlStringError() {
        return "Non hai abbastanza risorse per poter pagare";
    }

    public void setResourceList(ResourceList resourceList) {
        this.resourceList = resourceList;
    }

    public void resetResourceList() {
        this.resourceList = null;
    }

    /* Class Methods */
    @Override
    public boolean execute() {
        if(this.resourceList == null)
        {
            return false;
        }

        boolean ret = this.resourceList.lessOrEquals(player.getPlayerResources());
        resetResourceList();
        return ret;
    }

}
