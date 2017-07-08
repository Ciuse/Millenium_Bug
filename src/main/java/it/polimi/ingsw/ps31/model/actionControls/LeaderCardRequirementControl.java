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


    @Override
    public String getControlStringError() {
        return "Non hai abbastanza requisiti per poter usare la carta leader";
    }

    /* Resetters */
    public void resetLeaderCard()
    {
        this.leaderCard = null;
    }

    /* Execute Method */
    @Override
    public boolean execute() {
        boolean ret;
        if (leaderCard.getNumberOfCardOfTheSameType() == 0) {
            ret = player.getActionControlSet().developmentCardRequirementsControl(leaderCard.getDevelopmentCardRequest())
                    && player.getActionControlSet().resourceRequirementsControl(leaderCard.getResourceRequest());
            resetLeaderCard();
            return ret;
        } else{
            if(player.getPersonalBoard().getPlayerCardList().maxNumberOfCardOfTheSameType()>=leaderCard.getNumberOfCardOfTheSameType()){
                return true;
            }
            else return false;
        }
    }
}
