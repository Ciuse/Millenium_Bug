package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.bonus.Bonus;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 18/05/2017.
 */
public class BonusAndMalusEffect extends Effect{
    private final Bonus bonus;

    public BonusAndMalusEffect(int cardId,Bonus bonus) {
        super(cardId);
        this.bonus = bonus;
    }

    public Bonus getBonus() {
        return bonus;
    }

    @Override
    public void activate(Player player) {

    }
    @Override
    public String nameString() {
        return null;
    }

}
