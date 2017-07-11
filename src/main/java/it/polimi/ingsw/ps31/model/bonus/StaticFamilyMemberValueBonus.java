package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Francesco on 19/06/2017.
 *
 * Bonus che setta il valore dei famigliari colorati a un valore standard, indipendentemente da quale sia il valore del dado associato
 */
public class StaticFamilyMemberValueBonus extends Bonus {
    private final int modifyValueColoredFamilyMember;
    private List<DiceColor> membersToModify;

    /* Constructor */
    public StaticFamilyMemberValueBonus(int modifyValueColoredFamilyMember, List<DiceColor> membersToModify){
        this.modifyValueColoredFamilyMember = modifyValueColoredFamilyMember;
        this.membersToModify = membersToModify;
    }


    @Override
    public void activate(Player player) {
        for( DiceColor diceColor : membersToModify)
            player.fixFamilyMemberValue(diceColor, modifyValueColoredFamilyMember);

    }
}
