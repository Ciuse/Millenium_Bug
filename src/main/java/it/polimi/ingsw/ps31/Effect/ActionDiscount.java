package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Actions.Action;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class ActionDiscount extends Effect {
    private final Action actionToDiscount;
    private final int discountValue;
    public ActionDiscount(Action actionToDiscount, int discountValue){
        this.actionToDiscount = actionToDiscount;
        this.discountValue = discountValue;
    }

    @Override
    public void activate(Player player) {

    }
}
