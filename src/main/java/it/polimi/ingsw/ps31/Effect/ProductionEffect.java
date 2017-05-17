package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Player.Player;

import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
// generico effetto produzione
public class ProductionEffect extends Effect {
    private final int productionActionValue; // è il valore del dado che serve per attivare la produzione
    private final List<GetResource> getResourceList; // ogni produzione può avere una lista di effetti ottieni risorsa
    public ProductionEffect(int productionActionValue, List<GetResource> productionEffectList) {
        this.productionActionValue = productionActionValue;
        this.getResourceList = productionEffectList;
    }


    @Override
    public void activate(Player player) {

    }
}
