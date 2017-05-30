package it.polimi.ingsw.ps31.bonus;

import it.polimi.ingsw.ps31.actions.Action;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public abstract class Bonus {
    private final Action actionToModify;

    protected Bonus(Action actionToModify) {
        this.actionToModify = actionToModify;
    }

    public Action getActionToModify() {
        return actionToModify;
    }
}
