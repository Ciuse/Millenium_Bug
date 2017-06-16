package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class LostFinalVictoryPointFromPlayerResources extends Bonus {
    private final String string = "LostFinalVictoryPointFromPlayerResources";

    protected LostFinalVictoryPointFromPlayerResources(Action actionToModify, ResourceList resourceFinalPersonalBoardList, int lostPoint) {
        super(actionToModify);
    }

    public String getString() {
        return string;
    }

    @Override
    public void activate(Player player) {

    }
}