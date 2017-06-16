package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

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

    public int getDiceValue() {
        return harvestActionValue;
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

    public String nameString(){
        return "HarvEffect";
    }


}
