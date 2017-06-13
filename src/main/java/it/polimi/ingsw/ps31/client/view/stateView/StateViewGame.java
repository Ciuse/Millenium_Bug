package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateGame;

/**
 * Created by giulia on 12/06/2017.
 */
public class StateViewGame {
    private int period;
    private int round;
    private String namePlayerInAction;

    public void updateState(StateGame stateGame){
        if(stateGame.getPeriod()!=-1){
            this.period = stateGame.getPeriod();
            this.round = stateGame.getRound();
            this.namePlayerInAction = stateGame.getNamePlayerInAction();
        }
    }
}
