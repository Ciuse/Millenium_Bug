package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Giuseppe on 26/05/2017.
 */
public class GenericProductionActivation extends Effect {
    private final int basicValue;

    public GenericProductionActivation(int cardId,int basicValue) {
        super(cardId);
        this.basicValue = basicValue;
    }

    public GenericProductionActivation(int basicValue) {
        this.basicValue = basicValue;
    }

    public int getBasicValue() {
        return basicValue;
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
    public GetResourceEffect getGetResourceEffect() {
        return null;
    }

    @Override
    public String resourceDiscountString() {
        return null;
    }

    @Override
    public GetResourceEffectFromCard getGetResourceEffectFromCard() {
        return null;
    }

    @Override
    public ChangeResourceEffect getChangeResourceEffect() {
        return null;
    }

    @Override
    public void activate(Player player) {
        int diceValue = player.getLastUsedFamilyMember().getTotalValue()+basicValue;
        player.getPlayerActionSet().activateProduction(diceValue);
    }
    public String nameString(){
        return "ActProduction";
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
}
