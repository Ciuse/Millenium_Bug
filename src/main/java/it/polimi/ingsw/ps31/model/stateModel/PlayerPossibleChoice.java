package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.List;

/**
 * Created by Giuseppe on 20/06/2017.
 */
public class PlayerPossibleChoice {
    private PlayerId playerId;
    private List<Integer> leaderId;

    public PlayerPossibleChoice(PlayerId playerId, List<Integer> leaderId) {
        this.playerId = playerId;
        this.leaderId = leaderId;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public List<Integer> getLeaderId() {
        return leaderId;
    }
}
