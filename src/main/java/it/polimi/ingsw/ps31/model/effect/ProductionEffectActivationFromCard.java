package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 *
 * Effetto di attivazione di una produzione da carta. Funziona come l' attivazione generica di una produzione
 * solo che il valore con cui si attiva è descritto all'interno dell'effetto della carta
 */
public class ProductionEffectActivationFromCard extends GenericProductionEffectActivation {

    /**
     *
     * @param productionValue valore base con cui verrà creato l'effetto produzione
     */
    public ProductionEffectActivationFromCard(int cardId, int productionValue) {// rappresenta il valore con cui viene creato l'effetto produzione
        super(cardId,productionValue);
    }

    /**
     * Prima di invocare l'azione Produzione del player viene calcolato il valore del dado con cui
     * si dovrà attivare, ovvero con il valore descritto sull'effetto della carta
     * @see it.polimi.ingsw.ps31.model.actions.ActionActivateProduction
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        int diceValue = 0+ super.getBasicValue(); // passo zero perchè viene attivato tramite la carta e non più dalla board
        player.getPlayerActionSet().activateProduction(diceValue);//quando viene attivato da una carta facciamo finta che il famigliare che attiva l'effetto abbia valore zero
    }
    public String getNameString(){
        return "ProdFromCArd";
    }
}
