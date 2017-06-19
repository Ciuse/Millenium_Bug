package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public class ProductionBonus extends Bonus {
    private final int value;

    protected ProductionBonus(Action actionToModify, int value) {
        super(actionToModify);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getActivateProduction().addDiceBonus(value);
    }
}
