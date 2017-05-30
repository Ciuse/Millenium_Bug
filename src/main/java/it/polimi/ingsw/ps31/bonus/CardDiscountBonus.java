package it.polimi.ingsw.ps31.bonus;

import it.polimi.ingsw.ps31.actions.Action;
import it.polimi.ingsw.ps31.constants.CardColor;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public class CardDiscountBonus extends Bonus {
    private final int value;
    private final CardColor cardColor;
    protected CardDiscountBonus(Action actionToModify, int value, CardColor cardColor) {
        super(actionToModify);
        this.value = value;
        this.cardColor = cardColor;
    }

    public int getValue() {
        return value;
    }

    public CardColor getCardColor() {
        return cardColor;
    }
}
