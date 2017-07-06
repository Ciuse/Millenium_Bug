package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 *
 * Interfaccia implementata da tutti gli oggetti che possono attivare un bonus.
 * @see it.polimi.ingsw.ps31.model.effect.BonusAndMalusEffect
 * @see it.polimi.ingsw.ps31.model.card.ExcommunicationTiles
 */
public interface ActiveBonus {

    void activeBonus(Player player);

}
