package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.gameResource.PointResource;
import it.polimi.ingsw.ps31.model.player.Player;


/**
 * Created by giulia on 16/06/2017.
 */
public class LostFinalVictoryPointBonus extends  Bonus {
    private final PointResource pointResource;


    protected LostFinalVictoryPointBonus(PointResource pointResource) {
        this.pointResource = pointResource;
    }

    public PointResource getPointResource() {
        return pointResource;
    }

    @Override
    public void activate(Player player) {

    }
}