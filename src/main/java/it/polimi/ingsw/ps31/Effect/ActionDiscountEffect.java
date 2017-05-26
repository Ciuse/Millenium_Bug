package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Actions.Action;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class ActionDiscountEffect extends BonusAndMalusEffect {
    private final int discountValue;
    public ActionDiscountEffect(BonusAndMalusEffect bonusAndMalusEffect, int discountValue){
        super(bonusAndMalusEffect);
        this.discountValue = discountValue;
    }

    @Override
    public void activate(Player player) {
    }
}
