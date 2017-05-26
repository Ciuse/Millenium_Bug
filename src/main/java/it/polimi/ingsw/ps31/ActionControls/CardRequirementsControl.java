package it.polimi.ingsw.ps31.ActionControls;

import it.polimi.ingsw.ps31.GameThings.Resource;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 24/05/2017.
 */
public class CardRequirementsControl extends Control {
    private ResourceList requirement = null;

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

    public boolean execute()
    {
        if ( this.requirement == null )
        {
            //TODO: gestire
            return false;
        }
        else
        {
            boolean result = player.getResources().greaterThan(this.requirement);
            resetRequirements();
            return result;
        }

    }

    /* Constructor */
    public CardRequirementsControl(Player player) {
        super(player);
    }
}
