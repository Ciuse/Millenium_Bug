package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 20/06/2017.
 */
public class NoBoardRequirementControl extends Bonus {
    private final CardColor cardColor; // con la carta leader Cesare Borgia non devo soddisfare il requisitodi punti militare quando prendi le carte territorio

    public NoBoardRequirementControl(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getActionControlSet().getTakeDevelopmentCardControl().addColorCardToIgnore(cardColor);
    }
}
