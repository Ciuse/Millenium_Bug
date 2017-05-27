package it.polimi.ingsw.ps31.bonus;

import it.polimi.ingsw.ps31.Actions.Action;
import it.polimi.ingsw.ps31.Constants.CardColor;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public class CardDiscountBonus extends Bonus {
    private int value;
    private CardColor cardColor;
    protected CardDiscountBonus(Action actionToModify) {
        super(actionToModify);
    }
}
