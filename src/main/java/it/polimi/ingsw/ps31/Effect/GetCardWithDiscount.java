package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
public class GetCardWithDiscount extends GetCard {
    private final ResourceList resourcesDiscount; //la carta che devo prendere ha uno sconto sulle risorse
    public GetCardWithDiscount(List<CardColor> cardColors, int diceValue, ResourceList resourcesDiscount) {
        super(cardColors, diceValue);
        this.resourcesDiscount = resourcesDiscount;
    }
    @Override
    public void activate(Player player) {

    }
}
