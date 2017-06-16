package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class DiscountOnOccupatedTowerBox extends Bonus {
    private final int[] occupatedTowers;
    private final int discountTowerBox;


    protected DiscountOnOccupatedTowerBox(Action actionToModify, int[] occupatedTowers, int discountTowerBox) {
        super(actionToModify);
        this.occupatedTowers = occupatedTowers;
        this.discountTowerBox = discountTowerBox;
    }

    @Override
    public void activate(Player player) {

    }
}
