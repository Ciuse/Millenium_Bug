package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Actions.Action;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
public class CardCostDiscount extends BonusAndMalus{
    private final int discountValue;
    private final List<ResourceList> resourceDiscount;

    public CardCostDiscount(Action actionToDiscount, int discountValue, List<ResourceList> resourceDiscount) {
        super(actionToDiscount);
        this.discountValue = discountValue;
        this.resourceDiscount = resourceDiscount;
    }


    @Override
    public void activate(Player player) {

    }
}
