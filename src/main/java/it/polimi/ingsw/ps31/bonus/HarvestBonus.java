package it.polimi.ingsw.ps31.bonus;

import it.polimi.ingsw.ps31.actions.Action;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public class HarvestBonus extends Bonus {
    private final int value;
    protected HarvestBonus(Action actionToModify, int value) {
        super(actionToModify);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
