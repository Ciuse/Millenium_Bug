package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 27/05/2017.
 *
 * Bonus(o Malus) riguardante il valore con cui si attiva una Produzione
 *
 * @see it.polimi.ingsw.ps31.model.actions.ActionActivateProduction
 */
public class ProductionBonus extends Bonus {
    private final int value;

    public ProductionBonus(int value) {
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
