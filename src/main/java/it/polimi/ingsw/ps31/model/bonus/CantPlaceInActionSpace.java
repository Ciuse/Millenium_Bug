package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class CantPlaceInActionSpace extends Bonus{
    private final int[] actionSpaceId;

    protected CantPlaceInActionSpace(Action actionToModify, int[] actionSpaceId) {
        super(actionToModify);
        this.actionSpaceId = actionSpaceId;
    }

    @Override
    public void activate(Player player) {

    }
}
