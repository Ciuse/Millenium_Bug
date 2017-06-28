package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by giulia on 16/06/2017.
 */
public class CantPlaceInActionSpace extends Bonus{
    private List<Integer> actionSpaceIdList;

    /* Constructor */
    public CantPlaceInActionSpace(List<Integer> actionSpaceIdList){
        super();
        this.actionSpaceIdList = actionSpaceIdList;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getPlaceFamilyMemberInBoard().addDefaultDenyActionSpace(actionSpaceIdList);
    }
}
