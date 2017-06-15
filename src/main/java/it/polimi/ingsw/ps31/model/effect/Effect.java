package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.constants.CardColor;

import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class Effect extends ModelChoices implements EffectActivation{
    int cardId;

    public Effect(int cardId) {
        this.cardId = cardId;
    }

    public Effect(){}

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Effect effect = (Effect) o;

        return cardId == effect.cardId;
    }

    @Override
    public int hashCode() {
        return cardId;
    }

    public int getCardId() {
        return cardId;
    }

    public abstract String nameString();

    public abstract List<String> resourcesToPayString();

    public abstract List<String> resourcesToGainString();

    public abstract String resourceToGainString();

    public abstract String requiredResourceString();

    public abstract int getBasicValue();

    public abstract int getDiceValue();

    public abstract CardColor getCardColor();

    public abstract GetResourceEffect getGetResourceEffect();

    public abstract String resourceDiscountString();

    public abstract GetResourceEffectFromCard getGetResourceEffectFromCard();

    public abstract ChangeResourceEffect getChangeResourceEffect();



}
