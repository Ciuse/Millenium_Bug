package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class CanPlaceInOccupatedActionSpace extends Bonus {
    private final int[] occupatedActionSpace;


    protected CanPlaceInOccupatedActionSpace(Action actionToModify, int[] occupatedActionSpace) {
        super(actionToModify);
        this.occupatedActionSpace = occupatedActionSpace;
    }

    @Override
    public void activate(Player player) {
//        player.getActionControlSet().occupiedActionSpaceControl().
    }
}
