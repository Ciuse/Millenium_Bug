package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
// generico effetto produzione
public class ProductionEffect extends Effect {
    private final int productionActionValue; // è il valore del dado che serve per attivare la produzione
    private final GetResourceEffect getResourceEffect;
    private final ChangeResourceEffect changeResourceEffect;
    private final GetResourceEffectFromCard getResourceEffectFromCard;


    public ProductionEffect(int cardId,int productionActionValue,GetResourceEffect getResourceEffect, ChangeResourceEffect changeResourceEffect, GetResourceEffectFromCard getResourceEffectFromCard) {
        super(cardId);
        this.productionActionValue = productionActionValue;
        this.getResourceEffect = getResourceEffect;
        this.changeResourceEffect = changeResourceEffect;
        this.getResourceEffectFromCard = getResourceEffectFromCard;
    }

    public ProductionEffect(int productionActionValue, GetResourceEffect getResourceEffect, ChangeResourceEffect changeResourceEffect, GetResourceEffectFromCard getResourceEffectFromCard) {
        this.productionActionValue = productionActionValue;
        this.getResourceEffect = getResourceEffect;
        this.changeResourceEffect = changeResourceEffect;
        this.getResourceEffectFromCard = getResourceEffectFromCard;
    }

    public int getProductionActionValue() {
        return productionActionValue;
    }

    public ChangeResourceEffect getChangeResourceEffect() {
        return changeResourceEffect;
    }

    public GetResourceEffectFromCard getGetResourceEffectFromCard() {
        return getResourceEffectFromCard;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().activateProduction(productionActionValue);
    }

    @Override
    public GetResourceEffect getGetResourceEffect() {
        return getResourceEffect;
    }

    public String nameString(){
        return "ProdEffect";
    }

}
