package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 26/05/2017.
 * Effetto generico di attivazione di un raccolto ( presente sul tabellone)
 */
public class GenericHarvestEffectActivation extends Effect {
    /**
     * valore di attivazione base dell'effetto (in questa versione del gioco corrisponderà sempre a 0 o -3)
     */
    private final int basicValue;

    public GenericHarvestEffectActivation(int cardId, int basicValue){
        super(cardId);
        this.basicValue = basicValue;
    }

    public GenericHarvestEffectActivation(int basicValue) {
        this.basicValue = basicValue;
    }

    public int getBasicValue() {
        return basicValue;
    }

    /**
     * Prima di invocare l'azione Raccolto del player viene calcolato il valore del dado con cui
     * si dovrà attivare, ovvero sommando il valore del dado del famigliare con cui è stato attivato
     * l'effetto e il valore indicato dall'effetto
     * @see it.polimi.ingsw.ps31.model.actions.ActionActivateHarvest
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        int diceValue = player.getLastUsedFamilyMember().getTotalValue()+basicValue;
        player.getPlayerActionSet().activateHarvest(diceValue);
    }
    public String getNameString(){
        return "ActHarvest";
    }

}
