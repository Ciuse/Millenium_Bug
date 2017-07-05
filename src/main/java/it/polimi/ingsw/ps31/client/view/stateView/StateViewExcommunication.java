package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.stateModel.StateExcommunication;

/**
 * Created by Giuseppe on 04/07/2017.
 */
public class StateViewExcommunication {
    private final int period;
    private int id;
    private String bonusName;

    public StateViewExcommunication(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public String getBonusName() {
        return bonusName;
    }

    public int getId() {
        return id;
    }

    public void updateState(StateExcommunication stateExcommunication) {
        if (stateExcommunication.getPeriod() != 0) {
            bonusName=stateExcommunication.getBonusName();
            id=stateExcommunication.getId();
        }
    }

}
