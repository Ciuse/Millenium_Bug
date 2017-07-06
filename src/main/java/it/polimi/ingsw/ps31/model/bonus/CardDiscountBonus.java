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
    private final boolean anyColor;
    private ResourceList resourceListDiscount; //TODO IMPLEMENTARE NEI CONTROLLI

    public CardDiscountBonus(int value, CardColor cardColor, boolean anyColor) {
        super();
        this.value = value;
        this.cardColor = cardColor;
        this.anyColor = anyColor;
    }

    public CardDiscountBonus(int value, CardColor cardColor, boolean anyColor, ResourceList resourceListDiscount) {
        this.value = value;
        this.cardColor = cardColor;
        this.anyColor = anyColor;
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
            player.getPlayerActionSet().getActionControlSet().getTowerCardCostPlacementControl().addCardResourceDiscount(cardColor,anyColor,resourceListDiscount);
            player.getPlayerActionSet().getActionControlSet().getPayCardControl().addCardResourceDiscount(cardColor,anyColor,resourceListDiscount);
        }
        player.getPlayerActionSet().getActionControlSet().getDiceValueCardSpaceControl().addCardDiceBonus(cardColor, value);


    }
}
