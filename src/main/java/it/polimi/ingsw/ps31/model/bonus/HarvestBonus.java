package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.ActionActivateHarvest;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 27/05/2017.
 *
 * Bonus(o Malus) riguardante il valore con cui si attiva un Raccolto
 *
 * @see ActionActivateHarvest
 */
public class HarvestBonus extends Bonus {
    private final int value;

    public HarvestBonus( int value) {
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
