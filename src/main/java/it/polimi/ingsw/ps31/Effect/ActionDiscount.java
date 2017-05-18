package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Actions;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class ActionDiscount extends BonusAndMalus {
    private final int discountValue;
    public ActionDiscount(Actions actionToDiscount, int discountValue){
        super(actionToDiscount);
        this.discountValue = discountValue;
    }

    @Override
    public void activate(Player player) {

    }
}
