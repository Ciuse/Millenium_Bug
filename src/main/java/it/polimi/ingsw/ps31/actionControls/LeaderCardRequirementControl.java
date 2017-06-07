package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 31/05/2017.
 */
public class LeaderCardRequirementControl extends Control {
    private LeaderCard leaderCard;

    /* Constructor */
    public LeaderCardRequirementControl(Player player) {
        super(player);
    }

    /* Setters & Getters */
    public void setLeaderCard(LeaderCard leaderCard)
    {
        this.leaderCard = leaderCard;
    }

    public LeaderCard leaderCard()
    {
        return this.leaderCard;
    }

    /* Resetters */
    public void resetLeaderCard()
    {
        this.leaderCard = null;
    }

    /* Execute Method */
    @Override
    public boolean execute()
    {
        //Controllo che i parametri siano settati
        if ( this.leaderCard == null )
        {
            //todo: eccezione
            return false;
        }
        else
        {
            boolean ret;
            ret = player.getActionControlSet().developmentCardRequirementsControl(leaderCard.getDevelopmentCardRequest())
               && player.getActionControlSet().resourceRequirementsControl(leaderCard.getResourceRequest());
            resetLeaderCard();

            return ret;
        }

    }
}
