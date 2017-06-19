package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class ColoredFamilyMembersBonus extends Bonus {
    private final int bonus; //riduzione del valore dei familiari colorati quando li piazzo su uno spazio azione(scomunica)

    protected ColoredFamilyMembersBonus(int bonus) {
        super();
        this.bonus = bonus;
    }

    @Override
    public void activate(Player player) {

    }
}
