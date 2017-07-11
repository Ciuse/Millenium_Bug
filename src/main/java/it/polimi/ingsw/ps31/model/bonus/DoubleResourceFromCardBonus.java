package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 28/06/2017.
 *
 * Bonus della carta leader Santa Rita, il quale fa ottenere il doppio delle risorse quando si attiva un effetto
 * Immediato di una carta
 * @see it.polimi.ingsw.ps31.model.actions.ActionGetTempResourcesFromAllEffect
 */
public class DoubleResourceFromCardBonus extends Bonus {


    /**
     * attivo l attivazione doppia per le risorse ottenute da una carta
     */
    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getGetTempResources().setDoubleActivation(true);
    }
}
