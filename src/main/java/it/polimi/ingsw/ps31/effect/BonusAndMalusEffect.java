package it.polimi.ingsw.ps31.effect;

import it.polimi.ingsw.ps31.player.Player;
import it.polimi.ingsw.ps31.bonus.Bonus;

/**
 * Created by giulia on 18/05/2017.
 */
public class BonusAndMalusEffect extends Effect {
    private final Bonus bonusToSet;

    public BonusAndMalusEffect(Bonus bonusToSet) {
        this.bonusToSet = bonusToSet;
    }

    @Override
    public void activate(Player player) {

    }
}
