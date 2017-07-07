package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 * Interfaccia implementata da ogni bonus, per indicare il fatto che ogni bonus
 * deve specificare il modo in cui andrà a modificare il funzionamento dei controlli
 * o delle azioni associate a uno specifico player
 *
 * @see Bonus
 */
public interface BonusActivation {
    void activate(Player player);

}
