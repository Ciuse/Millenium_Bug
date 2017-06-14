package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.bonus.Bonus;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

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

    @Override
    public String nameString() {
        return null;
    }

    @Override
    public List<String> resourcesToPayString() {
        return null;
    }

    @Override
    public List<String> resourcesToGainString() {
        return null;
    }

    @Override
    public String resourceToGainString() {
        return null;
    }

    @Override
    public String requiredResourceString() {
        return null;
    }

    @Override
    public int getBasicValue() {
        return 0;
    }

    @Override
    public int getDiceValue() {
        return 0;
    }

    @Override
    public CardColor getCardColor() {
        return null;
    }

    @Override
    public Effect getGetResource() {
        return null;
    }

    @Override
    public GetResourceEffect getGetResourceEffect() {
        return null;
    }

    @Override
    public String resourceDiscountString() {
        return null;
    }
}
