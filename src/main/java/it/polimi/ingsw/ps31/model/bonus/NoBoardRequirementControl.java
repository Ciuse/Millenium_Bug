package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 20/06/2017.
 *
 * Bonus che ti permette di non soddisfare i requisiti di piazzamento nella board di un
 * specifico colore di carta
 *
 * @see it.polimi.ingsw.ps31.model.actionControls.TakeDevelopmentCardControl
 */
public class NoBoardRequirementControl extends Bonus {
    private final CardColor cardColor;

    public NoBoardRequirementControl(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getActionControlSet().getTakeDevelopmentCardControl().addColorCardToIgnore(cardColor);
    }
}
