package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.bonus.Bonus;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 18/05/2017.
 */
public class BonusAndMalusEffect extends Effect{
    private final Bonus bonusToSet;

    public BonusAndMalusEffect(int cardId,Bonus bonusToSet) {
        super(cardId);
        this.bonusToSet = bonusToSet;
    }

    @Override
    public void activate(Player player) {

    }
    @Override
    public String nameString() {
        return null;
    }

}
