package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 16/06/2017.
 *
 * Bonus (e Malus) che va ad alterare il valore di TUTTI i family member colorati del giocatore
 *
 * @see FamilyMember
 */
public class ColoredFamilyMembersBonus extends Bonus {
    /**
     * Valore (positivo o negativo) del valore da aggiungere al famigliare
     */
    private final int bonus;

    public ColoredFamilyMembersBonus(int bonus) {
        super();
        this.bonus = bonus;
    }

    @Override
    public void activate(Player player) {
        for (FamilyMember familyMember:player.getFamilyMembers()
             ) {
            if(!familyMember.getDiceColor().equals(DiceColor.NEUTRAL)){
                familyMember.addPermanentAdditionalValue(bonus);
            }
        }

    }
}
