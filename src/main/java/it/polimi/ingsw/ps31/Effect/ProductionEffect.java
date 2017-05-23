package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
// generico effetto produzione
public class ProductionEffect extends Effect {
    private final int productionActionValue; // è il valore del dado che serve per attivare la produzione
    private final Effect getResource; // ogni produzione ha un effetto ottieni risorsa
    public ProductionEffect(int productionActionValue, Effect productionEffect) {
        this.productionActionValue = productionActionValue;
        this.getResource = productionEffect;
    }


    @Override
    public void activate(Player player) {

    }
}
