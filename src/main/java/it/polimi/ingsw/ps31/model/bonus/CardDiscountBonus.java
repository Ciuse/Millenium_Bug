package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public class CardDiscountBonus extends Bonus {
    private final int value;
    private final CardColor cardColor;
    private ResourceList resourceListDiscount; //TODO IMPLEMENTARE NEI CONTROLLI

    public CardDiscountBonus(int value, CardColor cardColor) {
        super();
        this.value = value;
        this.cardColor = cardColor;
    }

    public CardDiscountBonus(int value, CardColor cardColor, ResourceList resourceListDiscount) {
        this.value = value;
        this.cardColor = cardColor;
        this.resourceListDiscount = resourceListDiscount;
    }

    public int getValue() {
        return value;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    @Override
    public void activate(Player player) {
        if(resourceListDiscount!=null){
            player.getPlayerActionSet().getActionControlSet().getTowerCardCostPlacementControl().addCardResourceDiscount(cardColor,resourceListDiscount);
            player.getPlayerActionSet().getPayCard().addCardResourceDiscount(cardColor,resourceListDiscount);
        }
        player.getPlayerActionSet().getActionControlSet().getDiceValueCardSpaceControl().addCardDiceBonus(cardColor, value);


    }
}
