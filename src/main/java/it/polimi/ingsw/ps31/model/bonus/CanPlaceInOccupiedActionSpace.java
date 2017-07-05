package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class CanPlaceInOccupiedActionSpace extends Bonus {

    /* Constructor */
    public CanPlaceInOccupiedActionSpace(){
        super();
    }

    @Override
    public void activate(Player player) {
        player.getActionControlSet().getOccupiedActionSpaceControl().setCanPlaceInAllOccupedActionSpace(true);
    }
}
