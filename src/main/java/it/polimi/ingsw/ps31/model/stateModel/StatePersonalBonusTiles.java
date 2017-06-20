package it.polimi.ingsw.ps31.model.stateModel;

import java.util.List;

/**
 * Created by giulia on 20/06/2017.
 */
public class StatePersonalBonusTiles extends StateType {
    private final List<StateEffect> stateEffectList;

    public StatePersonalBonusTiles(List<StateEffect> stateEffectList) {
        this.stateEffectList = stateEffectList;
    }


    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
