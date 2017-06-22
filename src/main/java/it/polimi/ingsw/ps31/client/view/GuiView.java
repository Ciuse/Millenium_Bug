package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewGame;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.List;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public abstract class GuiView extends View {
    public GuiView(PlayerId viewId, int playerMaxNumber) {
        super(viewId, playerMaxNumber);
    } //TODO TOGLIERE ABSTRACT ED IMPLEMEMTARE TUTTI I METODI


}
