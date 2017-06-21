package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 24/05/2017.
 */
public class ResourceRequirementsControl extends Control {
    private ResourceList requirement = null;

    /* Constructor */
    public ResourceRequirementsControl(Player player) {
        super(player);
    }

    @Override
    public String getControlStringError() {
        return "non hai abbastanza risorse richieste";
    }

    /* Setters & Getters */
    public void setRequirements(ResourceList requirement)
    {
        this.requirement = requirement;
    }

    public ResourceList getRequirement()
    {
        return this.requirement;
    }

    public void resetRequirements()
    {
        this.requirement = null;
    }

    /* Class Methods */
    @Override
    public boolean execute()
    {
        if ( this.requirement == null )
        {
            //TODO: gestire
            return false;
        }
        else
        {
            boolean result = player.getPlayerResources().greaterThan(this.requirement);
            resetRequirements();
            return result;
        }

    }

}
