package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by giulia on 16/06/2017.
 */
public class CanPlaceInOccupiedActionSpace extends Bonus {
    private List<Integer> actionSpaceIdList;
//    private final int[] occupatedActionSpace;

    /* Constructor */
    protected CanPlaceInOccupiedActionSpace(List<Integer> actionSpaceIdList){
        super();
        this.actionSpaceIdList = actionSpaceIdList;
    }

    @Override
    public void activate(Player player) {
        player.getActionControlSet().getOccupiedActionSpaceControl().addDefaultAllowedActionSpace(actionSpaceIdList);
    }
}
