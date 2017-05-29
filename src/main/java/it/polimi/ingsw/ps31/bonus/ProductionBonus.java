package it.polimi.ingsw.ps31.bonus;

import it.polimi.ingsw.ps31.actions.Action;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public class ProductionBonus extends Bonus {
    private int value;
    protected ProductionBonus(Action actionToModify) {
        super(actionToModify);
    }
}
