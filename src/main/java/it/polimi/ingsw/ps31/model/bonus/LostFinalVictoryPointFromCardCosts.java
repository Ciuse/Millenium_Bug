package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class LostFinalVictoryPointFromCardCosts extends Bonus {
    private  final ResourceList resourceList;
    private final CardColor cardColorForCostCard;

    public LostFinalVictoryPointFromCardCosts(ResourceList resourceList, CardColor cardColorForCostCard) {
        this.resourceList = resourceList;
        this.cardColorForCostCard = cardColorForCostCard;
    }

    public ResourceList getResourceList() {
        return resourceList;
    }

    public CardColor getCardColorForCostCard() {
        return cardColorForCostCard;
    }

    @Override
    public void activate(Player player) {

    }
}
