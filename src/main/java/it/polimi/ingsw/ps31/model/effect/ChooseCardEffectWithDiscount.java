package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class ChooseCardEffectWithDiscount extends ChooseCardEffect {
    private final ResourceList resourcesDiscount; //la carta che devo prendere ha uno sconto sulle risorse
    public ChooseCardEffectWithDiscount(int cardId,CardColor cardColor, int diceValue, ResourceList resourcesDiscount,boolean anyColor) {
        super(cardId,cardColor, diceValue, anyColor);
        this.resourcesDiscount = resourcesDiscount;
    }
    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().chooseCard(super.getCardColor(),super.getDiceValue(),super.isAnyColor(),this.resourcesDiscount);
    }
    public String getNameString(){
        return "ChooseCard+D";
    }

    public String resourceDiscountString() {
        return resourcesDiscount.toString();
    }
}
