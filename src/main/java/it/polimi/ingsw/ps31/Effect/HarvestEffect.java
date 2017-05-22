package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class HarvestEffect extends Effect {
    private final int harvestActionValue; // è il valore del dado che serve per attivare il raccolto
    private final GetResource getResource; //effetto che viene attivato se fai una produzione in cui ottengo una risorsa


    public HarvestEffect(int harvestActionValue,GetResource getResource) {
        this.getResource = getResource;
        this.harvestActionValue = harvestActionValue;
    }

    @Override
    public void activate(Player player) {

    }
}
