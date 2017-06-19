package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Francesco on 19/06/2017.
 */
public class FixedFamilyMemberValueChange extends Bonus {
    private final int modifyValueColoredFamilyMember;//(carta leader)i familiari colorati assumeranno un nuovo valore indipendentemente dal valore dei dadi
    private List<DiceColor> membersToModify;

    /* Constructor */
    public FixedFamilyMemberValueChange(int modifyValueColoredFamilyMember, List<DiceColor> membersToModify){
        this.modifyValueColoredFamilyMember = modifyValueColoredFamilyMember;
        this.membersToModify = membersToModify;
    }


    @Override
    public void activate(Player player) {
        for( DiceColor diceColor : membersToModify)
            player.fixFamilyMemberValue(diceColor, modifyValueColoredFamilyMember);

    }
}