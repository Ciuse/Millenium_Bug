package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.List;

/**
 * Created by giulia on 20/06/2017.
 */
public class StatePersonalBonusTiles extends StateType {
    private final PlayerId playerId;
    private final int personalBonusTilesId;
    private final List<StateEffect> stateEffectList;

    public StatePersonalBonusTiles(PlayerId playerId, int personalBonusTilesId, List<StateEffect> stateEffectList) {
        this.playerId = playerId;
        this.personalBonusTilesId = personalBonusTilesId;
        this.stateEffectList = stateEffectList;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public int getPersonalBonusTilesId() {
        return personalBonusTilesId;
    }

    public List<StateEffect> getStateEffectList() {
        return stateEffectList;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
