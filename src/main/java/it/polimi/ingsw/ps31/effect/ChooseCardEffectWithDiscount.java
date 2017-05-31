package it.polimi.ingsw.ps31.effect;

import it.polimi.ingsw.ps31.constants.CardColor;
import it.polimi.ingsw.ps31.gameThings.ResourceList;
import it.polimi.ingsw.ps31.player.Player;

import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
public class ChooseCardEffectWithDiscount extends ChooseCardEffect {
    private final ResourceList resourcesDiscount; //la carta che devo prendere ha uno sconto sulle risorse
    public ChooseCardEffectWithDiscount(List<CardColor> cardColors, int diceValue, ResourceList resourcesDiscount) {
        super(cardColors, diceValue);
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
}