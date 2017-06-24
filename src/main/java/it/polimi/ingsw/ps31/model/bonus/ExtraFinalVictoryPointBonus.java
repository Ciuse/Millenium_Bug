package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class ExtraFinalVictoryPointBonus extends Bonus {
    private final CardColor cardColor;


    protected ExtraFinalVictoryPointBonus(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public CardColor getCardColor(){
        return this.cardColor;
    }

    @Override
    public void activate(Player player) {

    }
}
