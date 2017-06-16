package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 */
public class ColoredFamilyMembersBonus extends Bonus {
    private final int bonus; //riduzione del valore dei familiari colorati quando li piazzo su uno spazio azione(scomunica)
    private final int modifyValueColoredFamilyMember;//(carta leader)i familiari colorati assumeranno un nuovo valore indipendentemente dal valore dei dadi

    protected ColoredFamilyMembersBonus(Action actionToModify, int bonus, int modifyValueColoredFamilyMember) {
        super(actionToModify);
        this.bonus = bonus;
        this.modifyValueColoredFamilyMember = modifyValueColoredFamilyMember;
    }

    @Override
    public void activate(Player player) {

    }
}
