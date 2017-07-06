package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 *
 * Effetto di attivazione di un raccolto da carta. Funziona come l' attivazione generica di un raccolto
 * solo che il valore con cui si attiva è descritto all'interno dell'effetto della carta
 */
public class HarvestEffectActivationFromCard extends GenericHarvestEffectActivation {

    /**
     ** @param harvestValue valore base con cui verrà creato l'effetto raccolto
     */
    public HarvestEffectActivationFromCard(int cardId, int harvestValue)
    {super(cardId,harvestValue);
    }


    /**
     * Prima di invocare l'azione Raccolto del player viene calcolato il valore del dado con cui
     * si dovrà attivare, ovvero con il valore descritto sull'effetto della carta
     * @see it.polimi.ingsw.ps31.model.actions.ActionActivateHarvest
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        int diceValue = 0+ super.getBasicValue();//quando viene attivato da una carta facciamo finta che il famigliare che attiva l'effetto abbia valore zero
        player.getPlayerActionSet().activateHarvest(diceValue);
    }

    @Override
    public String getNameString(){
        return "HarvFromCard";
    }
}
