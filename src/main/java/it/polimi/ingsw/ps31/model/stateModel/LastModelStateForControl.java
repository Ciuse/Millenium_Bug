package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.ModelChoices;

/**
 * Created by giulia on 19/06/2017.
 */
public class LastModelStateForControl {
    private  StateType stateForControl;

    public StateType getStateForControl() {
        return stateForControl;
    }

    public void setStateForControl(StateType stateForControl) {
        this.stateForControl = stateForControl;
    }
}