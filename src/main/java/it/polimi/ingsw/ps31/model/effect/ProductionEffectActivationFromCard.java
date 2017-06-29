package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class ProductionEffectActivationFromCard extends GenericProductionEffectActivation {

    public ProductionEffectActivationFromCard(int cardId, int productionValue) {// rappresenta il valore con cui viene creato l'effetto produzione
        super(cardId,productionValue);
    }

    @Override
    public void activate(Player player) {
        int diceValue = 0+ super.getBasicValue(); // passo zero perchè viene attivato tramite la carta e non più dalla board
        player.getPlayerActionSet().activateHarvest(diceValue);//quando viene attivato da una carta facciamo finta che il famigliare che attiva l'effetto abbia valore zero
    }
    public String getNameString(){
        return "ProdFromCArd";
    }
}
