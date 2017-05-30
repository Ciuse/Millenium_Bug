package it.polimi.ingsw.ps31.bonus;

import it.polimi.ingsw.ps31.actions.Action;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public class NoImmediateEffectBonus extends Bonus {
    private final int[] arrayValue;
    protected NoImmediateEffectBonus(Action actionToModify, int[] arrayValue) {
        super(actionToModify);
        this.arrayValue = arrayValue;
    }

    public int[] getValue() {
        return arrayValue.clone();
    }
}
