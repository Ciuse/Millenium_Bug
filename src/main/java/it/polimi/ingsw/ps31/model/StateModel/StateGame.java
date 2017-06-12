package it.polimi.ingsw.ps31.model.StateModel;

/**
 * Created by giulia on 06/06/2017.
 */
public class StateGame extends StateInfo {
    private int period=-1;
    private int round=-1;
    private String namePlayerInAction=null;

    public StateGame(int period, int round, String namePlayerInAction) {
        this.period = period;
        this.round = round;
        this.namePlayerInAction = namePlayerInAction;
    }

    public int getPeriod() {
        return period;
    }

    public int getRound() {
        return round;
    }

    public String getNamePlayerInAction() {
        return namePlayerInAction;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}

