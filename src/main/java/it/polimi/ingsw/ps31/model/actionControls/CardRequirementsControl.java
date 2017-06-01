package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.gameThings.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 24/05/2017.
 */
public class CardRequirementsControl extends Control {
    private ResourceList requirement = null;

    /* Constructor */
    public CardRequirementsControl(Player player) {
        super(player);
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
