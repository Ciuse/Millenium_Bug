package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.stateModel.StateMarkerDisc;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 12/06/2017.
 */
public class StateViewMarkerDisc {
    private final PlayerId playerId;
    private final String stringResourceType;
    private int value;

    public StateViewMarkerDisc(PlayerId playerId, String stringResourceType) {
        this.playerId = playerId;
        this.stringResourceType = stringResourceType;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public String getStringResourceType() {
        return stringResourceType;
    }

    public int getValue() {
        return value;
    }

    public void updateState(StateMarkerDisc stateMarkerDisc){
        if(stateMarkerDisc.getStringResourceType().equals(stringResourceType)){
            this.value = stateMarkerDisc.getValue();
        }
    }
}
