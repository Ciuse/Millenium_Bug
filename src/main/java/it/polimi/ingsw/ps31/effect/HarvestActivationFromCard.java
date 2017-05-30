package it.polimi.ingsw.ps31.effect;

import it.polimi.ingsw.ps31.player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class HarvestActivationFromCard extends GenericHarvestActivation {

    public HarvestActivationFromCard(int harvestValue)// rappresenta il valore con cui viene creato l'effetto raccolto
    {super(harvestValue);
    }


    @Override
    public void activate(Player player) {
        int diceValue = 0+ super.getBasicValue(); // passo zero perchè viene attivato tramite la carta e non più dalla board
        player.getPlayerActionSet().activateHarvest(diceValue);//quando viene attivato da una carta facciamo finta che il famigliare che attiva l'effetto abbia valore zero
    }
}
