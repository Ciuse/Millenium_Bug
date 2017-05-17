package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
public class CardCostDiscount extends Effect{
    private final CardColor cardColor;
    private final List<ResourceList> resourceDiscount;

    public CardCostDiscount(CardColor cardColor, List<ResourceList> resourceDiscount) {
        this.cardColor = cardColor;
        this.resourceDiscount = resourceDiscount;
    }


    @Override
    public void activate(Player player) {

    }
}
