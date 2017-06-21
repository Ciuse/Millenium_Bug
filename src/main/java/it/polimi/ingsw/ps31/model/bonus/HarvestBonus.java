package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public class HarvestBonus extends Bonus {
    private final int value;

    protected HarvestBonus( int value) {
        super();
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getActivateHarvest().addDiceBonus(value);
    }
}
