package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 20/06/2017.
 */
public class FixedMilitaryPointRequiredToTakeTerritoryCard extends Bonus {
    private final PointResource modifyValueResourceRequired; // con la carta leader Cesare Borgia non devo soddisfare il requisito
    private final CardColor cardColor;                     // di punti militare quando prendi le carte territorio, metto tutte a zero

    public FixedMilitaryPointRequiredToTakeTerritoryCard(PointResource modifyValueResourceRequired, CardColor cardColor) {
        this.modifyValueResourceRequired = modifyValueResourceRequired;
        this.cardColor = cardColor;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getActionControlSet().getTakeDevelopmentCardControl().addColorCardToIgnore(cardColor);
    }
}
