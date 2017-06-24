package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

import java.util.List;

/**
 * Created by giulia on 19/06/2017.
 */
public class LastModelStateForControl {
    private  StateType stateForControl;
    private List<ResourceList> resourceListToControl;

    public StateType getStateForControl() {
        return stateForControl;
    }

    public void setStateForControl(StateType stateForControl) {
        this.stateForControl = stateForControl;
    }

    public List<ResourceList> getResourceListToControl() {
        return resourceListToControl;
    }

    public void setResourceListToControl(List<ResourceList> resourceListToControl) {
        this.resourceListToControl = resourceListToControl;
    }
}
