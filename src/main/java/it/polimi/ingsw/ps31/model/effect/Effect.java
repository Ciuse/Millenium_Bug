package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.ModelChoices;
import it.polimi.ingsw.ps31.model.bonus.Bonus;
import it.polimi.ingsw.ps31.model.constants.CardColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class Effect extends ModelChoices implements EffectActivation{
    int cardId;
    int actionSpaceId;
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

    public String nameString(){
        return null;
    }

    public List<String> getResourceToPayListString(){
        return  new ArrayList<>();
    }

    public List<String> getResourceToGainListString() {
        return new ArrayList<>();
    }

    public String getResourceToGainString(){
        return null;
    }

    public String getRequiredResourceString() {
        return null;
    }

    public int getBasicValue(){
        return +10;
    }

    public int getDiceValue(){
        return -1;
    }

    public CardColor getCardColor(){
        return null;
    }

    public boolean isAnyColor() {
        return false;
    }

    public GetResourceEffect getGetResourceEffect(){
        return null;
    }

    public String resourceDiscountString(){
        return null;
    }

    public GetResourceEffectFromCard getGetResourceEffectFromCard(){
        return null;
    }

    public ChangeResourceEffect getChangeResourceEffect(){
        return null;
    }

    public Bonus getBonus(){
        return null;
    }

}
