package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 24/05/2017.
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
            //TODO: gestire
            return false;
        }

        boolean ret = player.getPlayerResources().greaterThan(this.resourceList);
        resetResourceList();

        return ret;
    }

}
