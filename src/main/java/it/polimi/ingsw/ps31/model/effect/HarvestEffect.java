package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 *
 * Effetto che rappresenta un raccolto permanente che il player avrà sempre a disposizione
 */
public class HarvestEffect extends Effect {
    /**
     * valore del dado che serve per attivare il raccolto indicato sull'effetto permanente della carta
     */
    private final int harvestActionValue;
    /**
     * effeto che viene attivato qual'ora venga rispettato il requisito di attivazione (in questa versione del gioco il raccolto sarà sempre
     *                                                                                  un effetto di tipo getResource)ù
     *
     * @see GetResourceEffect
     */
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

    /**
     * L'attivazione dell'effetto aggiunge alla lista dei possibili raccolti
     * attivabili del player anche se stesso
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        player.getHarvestList().addEffect(this);
    }

    public String getNameString(){
        return "HarvEffect";
    }


}
