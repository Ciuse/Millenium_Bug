package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.model.player.Player;

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

    @Override
    public String getControlStringError() {
        return "Non hai abbastanza requisiti \"Carte\"";
    }

    /* Resetters */
    public void resetRequirements()
    {
        this.requirement = null;
    }

    /* Class Methods */
    @Override
    public boolean execute() {
        if ( this.requirement == null )
        {
            //La carta ha una lista di costi di carte "null"
            return true;
        }

        boolean result = this.requirement.lessOrEquals(player.getPlayerCardList());
        resetRequirements();
        return result;
    }


}
