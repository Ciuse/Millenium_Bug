package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
public class ChooseCardEffectWithDiscount extends ChooseCardEffect {
    private final ResourceList resourcesDiscount; //la carta che devo prendere ha uno sconto sulle risorse

    public ChooseCardEffectWithDiscount(CardColor cardColor, int diceValue, ResourceList resourcesDiscount, boolean anyColor) {
        super(cardColor, diceValue, anyColor);
        this.resourcesDiscount = resourcesDiscount;
    }
    @Override
    public void activate(Player player) {
//        ChooseCardAction chooseCardAction = new ChooseCardEffect();
//        chooseCardAction.setDiceCost(diceValue);
//        chooseCardAction.setCardColor(cardColors);
//        chooseCardAction.setDiscount(resourcesDiscount);
//        chooseCardAction.activate();
    }
    public String nameString(){
        return "ChooseCard+D";
    }

    public String resourceDiscountString() {
        return resourcesDiscount.toString();
    }
}
