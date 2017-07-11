package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 24/05/2017.
 *
 * Controllo se il giocatore ha soddisfatto i requisiti di tipo risorsa
 * scritti su una carta (leader)
 */
public class ResourceRequirementsControl extends Control {
    private ResourceList requirement = null;

    /* Constructor */
    public ResourceRequirementsControl(Player player) {
        super(player);
    }


    /* Setters & Getters */
    public void setRequirements(ResourceList requirement)
    {
        this.requirement = requirement;
    }

    @Override
    public String getControlStringError() {
        return "Non hai abbastanza requisiti \"Risorse\"";
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
            //La carta ha una lista di risorse "null"
            return true;
        }
        else
        {
            boolean result = this.requirement.lessOrEquals(player.getPlayerResources());
            resetRequirements();
            return result;
        }

    }

}
