package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class ModifyPayServantsBonus extends Bonus {
    private final int servantsToPayPerUnitaryDiceValueArise; //per aumentare il valore di un'azione ora devo pagare un numero di servitori in più

    public ModifyPayServantsBonus(int servantsToPayPerUnitaryDiceValueArise) {
        this.servantsToPayPerUnitaryDiceValueArise = servantsToPayPerUnitaryDiceValueArise;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getPayServants().setServantsPerDiceRise(servantsToPayPerUnitaryDiceValueArise);
    }
}
