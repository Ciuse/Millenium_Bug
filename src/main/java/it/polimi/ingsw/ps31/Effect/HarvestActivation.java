package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class HarvestActivation extends Effect {
    private final int harvestValue; // rappresenta il valore con cui viene creato l'effetto raccolto

    public HarvestActivation(int harvestValue) {
        this.harvestValue = harvestValue;
    }

    @Override
    public void activate(Player player) {

    }
}
