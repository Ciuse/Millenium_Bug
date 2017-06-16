package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;


/**
 * Created by giulia on 16/06/2017.
 */
public class LostFinalVictoryPointBonus extends  Bonus {
    private final PointResource pointResource;


    protected LostFinalVictoryPointBonus(Action actionToModify, PointResource pointResource) {
        super(actionToModify);
        this.pointResource = pointResource;
    }

    public PointResource getPointResource() {
        return pointResource;
    }
}