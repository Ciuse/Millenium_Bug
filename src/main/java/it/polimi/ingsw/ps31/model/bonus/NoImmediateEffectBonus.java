package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public class NoImmediateEffectBonus extends Bonus {

    public NoImmediateEffectBonus() {
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getPlaceFamilyMemberInTower().setImmediateEffectsAreActivable(false);
    }
}
