package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 30/06/2017.
 */
public class NoFirstActionTurn extends Bonus {
    //Ogni turno di gioco il giocatore deve saltare la sua prima azione perchè ha la scomunica
    @Override
    public void activate(Player player) {
        player.setFlagTurnExcommunication(1);
    }
}
