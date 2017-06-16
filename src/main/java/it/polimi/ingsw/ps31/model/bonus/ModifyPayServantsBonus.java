package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class ModifyPayServantsBonus extends Bonus {
    private final int diceRisePerServant;

    protected ModifyPayServantsBonus(Action actionToModify, int diceRisePerServant) {
        super(actionToModify);
        this.diceRisePerServant = diceRisePerServant;
    }

    @Override
    public void activate(Player player) {

    }
}