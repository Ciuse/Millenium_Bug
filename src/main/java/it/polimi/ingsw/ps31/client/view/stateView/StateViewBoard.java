package it.polimi.ingsw.ps31.client.view.stateView;

import java.util.List;

/**
 * Created by giulia on 07/06/2017.
 */
public class StateViewBoard {
    private final List<StateViewTower> stateViewTowerList;
    private final List<StateViewActionSpace> stateViewActionSpaceList;

    public StateViewBoard(List<StateViewTower> stateViewTowerList, List<StateViewActionSpace> stateViewActionSpaceList) {
        this.stateViewTowerList = stateViewTowerList;
        this.stateViewActionSpaceList = stateViewActionSpaceList;
    }
}
