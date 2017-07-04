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

    @Override
    public String getControlStringError() {
        return "Non hai abbastanza risorse per poter pagare"+1/0;       //TODO TOGLIERE ASSOLUTAMENTE IL /0
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

        boolean ret = this.resourceList.lessOrEquals(player.getPlayerResources());
        resetResourceList();
        return ret;
    }

}
