package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.model.actions.Action;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 27/05/2017.
 */
public class NoImmediateEffectBonus extends Bonus {
    private final List<Integer> arrayValue;
    protected NoImmediateEffectBonus(Action actionToModify, ArrayList<Integer> arrayValue) {
        super(actionToModify);
        this.arrayValue = arrayValue;
    }

    public List<Integer> getValue() {
        return arrayValue.subList(0, arrayValue.size()-1);
    }

    @Override
    public void activate(Player player) {

    }
}
