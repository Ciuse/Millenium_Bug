package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

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

    public int getProductionActionValue() {
        return productionActionValue;
    }

    public Effect getGetResource() {
        return getResource;
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
    public void activate(Player player) {

    }

    public String nameString(){
        return "ProdEffect";
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
