package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public class CardDiscountBonus extends Bonus {
    private final int value;
    private final CardColor cardColor;
    protected CardDiscountBonus(int value, CardColor cardColor) {
        super();
        this.value = value;
        this.cardColor = cardColor;
    }

    public int getValue() {
        return value;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getTakeCard().addCardDiceBonus(cardColor, value);
    }
}
