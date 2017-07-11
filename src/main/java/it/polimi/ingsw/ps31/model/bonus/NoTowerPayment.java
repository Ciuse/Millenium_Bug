package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 *
 * Bonus che ti permette di non pagare la torre
 *
 * @see it.polimi.ingsw.ps31.model.actions.ActionPayTowerMoney
 */
public class NoTowerPayment extends Bonus {

    public NoTowerPayment() {
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getPayTowerMoney().setToPay(false);
    }
}
