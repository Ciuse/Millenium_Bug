package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateGame;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 12/06/2017.
 */
public class StateViewGame {
    private int period;
    private int round;
    private PlayerId playerIdInACtion;

    public void updateState(StateGame stateGame){
        if(stateGame.getPeriod()!=-1){
            this.period = stateGame.getPeriod();
            this.round = stateGame.getRound();
            this.playerIdInACtion = stateGame.getPlayerIdInAction();
        }
    }

    public int getPeriod() {
        return period;
    }

    public int getRound() {
        return round;
    }

    public PlayerId getPlayerIdInACtion() {
        return playerIdInACtion;
    }
}
