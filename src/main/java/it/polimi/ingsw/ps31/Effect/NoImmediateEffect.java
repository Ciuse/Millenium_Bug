package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Actions.Action;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.List;

/**
 * Created by giulia on 18/05/2017.
 */
// vengono vietati gli effetti immediati degli spazi azioni sul tabellone nel piano specificato dall'effetto in questione
public class NoImmediateEffect extends BonusAndMalusEffect {
    private final List<Integer> actionSpaceValue; //è il valore dello spazio azione a cui verrà negato l'effetto immediato

    public NoImmediateEffect( BonusAndMalusEffect bonusAndMalusEffect, List<Integer> actionSpaceValue) {
        super(bonusAndMalusEffect);
        this.actionSpaceValue = actionSpaceValue;
    }

    @Override
    public void activate(Player player) {

    }
}
