package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 27/05/2017.
 *
 *Interfaccia implementata da tutti gli oggetti che possono attivare un effetto.
 * @see it.polimi.ingsw.ps31.model.board.ActionSpace
 * @see it.polimi.ingsw.ps31.model.card.DevelopmentCard
 * @see it.polimi.ingsw.ps31.model.card.LeaderCard
 */
public interface ActiveEffect {

    void activeEffectList(Player player);
}
