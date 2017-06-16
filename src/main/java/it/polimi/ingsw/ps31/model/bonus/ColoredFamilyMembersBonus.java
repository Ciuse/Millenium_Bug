package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;

/**
 * Created by giulia on 16/06/2017.
 */
public class ColoredFamilyMembersBonus extends Bonus {
    private final int bonus;

    protected ColoredFamilyMembersBonus(Action actionToModify, int bonus) {
        super(actionToModify);
        this.bonus = bonus;
    }
}
