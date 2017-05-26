package it.polimi.ingsw.ps31.ActionControls;

import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

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

    @Override
    public boolean execute() {
        if(this.resourceList == null)
        {
            //TODO: gestire
            return false;
        }

        boolean ret = (player.getResources().greaterThan(this.resourceList));
        resetResourceList();

        return ret;
    }

}
