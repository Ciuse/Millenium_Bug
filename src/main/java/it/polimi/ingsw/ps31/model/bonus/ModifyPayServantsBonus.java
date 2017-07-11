package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 *
 * Malus che va a influenzare il rapporto tra quanti servitori devi pagare per aumentare il valore del dado del famigliare
 *
 * @see it.polimi.ingsw.ps31.model.actions.ActionPayServants
 */
public class ModifyPayServantsBonus extends Bonus {
    private final int servantsToPayPerUnitaryDiceValueArise;

    public ModifyPayServantsBonus(int servantsToPayPerUnitaryDiceValueArise) {
        this.servantsToPayPerUnitaryDiceValueArise = servantsToPayPerUnitaryDiceValueArise;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getPayServants().setServantsPerDiceRise(servantsToPayPerUnitaryDiceValueArise);
    }
}
