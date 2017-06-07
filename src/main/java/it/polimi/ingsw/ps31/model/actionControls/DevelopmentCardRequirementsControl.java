package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.card.DevelopmentCard;
import it.polimi.ingsw.ps31.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.gameThings.ResourceList;
import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by Francesco on 31/05/2017.
 */
public class DevelopmentCardRequirementsControl extends Control{
    private DevelopmentCardList requirement = null;

    /* Constructor */
    public DevelopmentCardRequirementsControl(Player player) {
        super(player);
    }

    /* Setters & Getters */
    public void setRequirements(DevelopmentCardList requirement)
    {
        this.requirement = requirement;
    }

    public DevelopmentCardList getRequirement()
    {
        return this.requirement;
    }

    /* Resetters */
    public void resetRequirements()
    {
        this.requirement = null;
    }

    /* Class Methods */
    @Override
    public boolean execute()
    {
        //controllo che i parametri siano settati
        if ( this.requirement == null )
        {
            //TODO: gestire
            return false;
        }
        else
        {
            boolean result = player.getPlayerCardList().lessOrEquals(this.requirement);
            resetRequirements();
            return result;
        }
    }

}