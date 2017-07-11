package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 27/05/2017.
 *
 * Malus che ti impedisce di ottenere Risorse dall' attivazione di un effetto immediato degli action space delle torri
 *
 * @see it.polimi.ingsw.ps31.model.actions.ActionPlaceFamilyMemberInTower
 */
public class NoImmediateEffectBonus extends Bonus {

    public NoImmediateEffectBonus() {
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getPlaceFamilyMemberInTower().setImmediateEffectsAreActivable(false);
    }
}
