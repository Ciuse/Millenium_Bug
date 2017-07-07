package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 *
 *  * Effetto che rappresenta una produzione permanente che il player avrà sempre a disposizione

 */
public class ProductionEffect extends Effect {
    /**
     * valore del dado che serve per attivare il raccolto indicato sull'effetto permanente della carta
     */
    private final int productionActionValue;
    /**
     * effeto che viene attivato qual'ora venga rispettato il requisito di attivazione (in questa versione del gioco i possibili
     * effetti saranno solo 3. E solo uno di loro sarà non nullo
     * @see GetResourceEffect
     * @see ChangeResourceEffect
     * @see GetResourceEffectFromCard
     */
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

    public int getDiceValue() {
        return productionActionValue;
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

    /**
     * L'attivazione dell'effetto aggiunge alla lista delle possibili produzioni
     * attivabili del player anche se stesso
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        player.getProductionList().addEffect(this);
    }

    @Override
    public GetResourceEffect getGetResourceEffect() {
        return getResourceEffect;
    }

    public String getNameString(){
        return "ProdEffect";
    }

}
