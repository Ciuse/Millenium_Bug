package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.List;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public abstract class GuiView extends View { //TODO TOGLIERE ABSTRACT ED IMPLEMEMTARE TUTTI I METODI

    public GuiView(PlayerId viewId, StateViewBoard stateViewBoard, List<StateViewPersonalBoard> stateViewPersonalBoard, List<StateViewPlayer> stateViewPlayer) {
        super(viewId, stateViewBoard, stateViewPersonalBoard, stateViewPlayer);
    }
}
