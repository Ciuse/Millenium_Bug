package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.stateModel.StateEffect;

import java.util.List;

/**
 * Created by giulia on 23/06/2017.
 */
public class StateViewPersonalBonusTiles  {
    private final PlayerId playerId;
    private final int personalBonusTilesId;
    private final List<StateViewEffect> stateEffectList;

    public StateViewPersonalBonusTiles(PlayerId playerId, int personalBonusTilesId, List<StateViewEffect> stateEffectList) {
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

    public List<StateViewEffect> getStateEffectList() {
        return stateEffectList;
    }
}
