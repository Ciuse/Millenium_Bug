package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 30/06/2017.
 *
 * Malus che impone al giocatore di saltare la prima azione di ogni turno e di recuperarla alla fine
 *
 * @see it.polimi.ingsw.ps31.model.game.GameLogic
 */
public class NoFirstActionTurn extends Bonus {

    /**
     * Viene Attivato settando un flag al giocatore
     * @param player
     */
    @Override
    public void activate(Player player) {
        player.setFlagTurnExcommunication(1);
    }
}
