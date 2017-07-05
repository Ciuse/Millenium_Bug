package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 06/06/2017.
 * Stato che indica la fase in cui si trova la partita e il giocatore in gioco
 *
 * @see StateType
 * @see it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState
 * @see it.polimi.ingsw.ps31.model.game.GameLogic
 *
 */
public class StateGame extends StateType {
    private int period=-1;
    private int round=-1;
    private PlayerId playerIdInAction=null;

    public StateGame(int period, int round, PlayerId playerIdInAction) {
        this.period = period;
        this.round = round;
        this.playerIdInAction=playerIdInAction;
    }

    public int getPeriod() {
        return period;
    }

    public int getRound() {
        return round;
    }

    public PlayerId getPlayerIdInAction() {
        return playerIdInAction;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}

