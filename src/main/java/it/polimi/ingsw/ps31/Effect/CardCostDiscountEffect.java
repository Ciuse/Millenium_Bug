package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Actions.Action;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
public class CardCostDiscountEffect extends BonusAndMalusEffect {
    private final int discountValue;
    private final List<ResourceList> resourceDiscount;

    public CardCostDiscountEffect(BonusAndMalusEffect bonusAndMalusEffect, int discountValue, List<ResourceList> resourceDiscount) {
        super(bonusAndMalusEffect);
        this.discountValue = discountValue;
        this.resourceDiscount = resourceDiscount;
    }


    @Override
    public void activate(Player player) {

    }
}
