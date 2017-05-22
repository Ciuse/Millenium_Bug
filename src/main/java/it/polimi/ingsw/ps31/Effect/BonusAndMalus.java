package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Actions.Actions;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by giulia on 18/05/2017.
 */
public class BonusAndMalus extends Effect {
    private final Actions actionToDiscount;

    public BonusAndMalus(Actions actionToDiscount) {
        this.actionToDiscount = actionToDiscount;
    }


    @Override
    public void activate(Player player) {

    }
}
