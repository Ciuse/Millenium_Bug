package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 28/06/2017.
 */
public class DoubleResourceFromCardBonus extends Bonus {    //bonus santa rita


    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getGetTempResources().setDoubleActivation(true); //attivo l attivazione doppia per le risorse ottenute da una carta
    }
}
