package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
public class HarvestEffect extends Effect {
    private final int harvestActionValue; // è il valore del dado che serve per attivare il raccolto
    private final GetResourceEffect getResourceEffect; //effetto che viene attivato se fai una produzione in cui ottengo una risorsa


    public HarvestEffect(int cardId,int harvestActionValue,GetResourceEffect getResourceEffect) {
        super(cardId);
        this.getResourceEffect = getResourceEffect;
        this.harvestActionValue = harvestActionValue;
    }

    public HarvestEffect(int harvestActionValue, GetResourceEffect getResourceEffect) {
        this.harvestActionValue = harvestActionValue;
        this.getResourceEffect = getResourceEffect;
    }

    public int getHarvestActionValue() {
        return harvestActionValue;
    }

    public GetResourceEffect getGetResourceEffect() {
        return getResourceEffect;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().activateHarvest(harvestActionValue);
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

    public String nameString(){
        return "HarvEffect";
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

}
