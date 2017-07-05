package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class LostFinalVictoryPointFromPlayerResources extends Bonus {
    private final String string = "LostFinalVictoryPointFromPlayerResources";

    public LostFinalVictoryPointFromPlayerResources() {
       
    }

    public String getString() {
        return string;
    }

    @Override
    public void activate(Player player) {

    }
}
