package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class ProductionActivationFromCard extends GenericProductionActivation {
    private final int productionValue; // rappresenta il valore con cui viene creato l'effetto produzione

    public ProductionActivationFromCard(int productionValue) {
        super(0);
        this.productionValue = productionValue;
    }

    @Override
    public void activate(Player player) {

    }

}
