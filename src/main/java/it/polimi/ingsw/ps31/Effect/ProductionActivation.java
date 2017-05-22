package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class ProductionActivation extends Effect {
    private final int productionValue; // rappresenta il valore con cui viene creato l'effetto produzione

    public ProductionActivation(int productionValue) {
        this.productionValue = productionValue;
    }

    @Override
    public void activate(Player player) {

    }

}
