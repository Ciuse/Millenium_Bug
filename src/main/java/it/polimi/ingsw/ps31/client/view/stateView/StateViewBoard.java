package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateActionSpace;
import it.polimi.ingsw.ps31.model.StateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.StateModel.StateMarkerDisc;
import it.polimi.ingsw.ps31.model.StateModel.StateTower;

import java.util.List;

/**
 * Created by giulia on 07/06/2017.
 */
public class StateViewBoard {
    private final List<StateViewTower> stateViewTowerList;
    private final List<StateViewActionSpace> stateViewActionSpaceList;
    private final List<StateViewMarkerDisc> stateViewMarkerDiscList;

    public StateViewBoard(List<StateViewTower> stateViewTowerList, List<StateViewActionSpace> stateViewActionSpaceList, List<StateViewMarkerDisc> stateViewMarkerDiscList) {
        this.stateViewTowerList = stateViewTowerList;
        this.stateViewActionSpaceList = stateViewActionSpaceList;
        this.stateViewMarkerDiscList = stateViewMarkerDiscList;
    }
    public void updateState(StateActionSpace stateActionSpace){
        for (StateViewActionSpace stateViewActionSpace : stateViewActionSpaceList
                ) { if(stateActionSpace.getNumberOfActionSpace()==stateViewActionSpace.getNumberOfActionSpace())
                    stateViewActionSpace.updateState(stateActionSpace);

        }
    }
    public void updateState(StateTower stateTower){
        for (StateViewTower stateViewTower: stateViewTowerList
                ) { if(stateTower.getColor().equals(stateViewTower.getTowerColor())){
            for (StateCardBox stateCardBox: stateTower.getTowerCardSpaceList()
                 ) {
                stateViewTower.updateState(stateCardBox);
                }
                }
        }
    }

    public void updateState(StateMarkerDisc stateMarkerDisc){
        for (StateViewMarkerDisc stateViewMarkerDisc : stateViewMarkerDiscList
                ) {
            if(stateMarkerDisc.getStringResourceType().equals(stateViewMarkerDisc.getStringResourceType())&&
                    stateMarkerDisc.getPlayerId().equals(stateViewMarkerDisc.getPlayerId())){
                stateViewMarkerDisc.updateState(stateMarkerDisc);
            }
        }
    }

    public List<StateViewTower> getStateViewTowerList() {
        return stateViewTowerList;
    }

    public List<StateViewActionSpace> getStateViewActionSpaceList() {
        return stateViewActionSpaceList;
    }

    public List<StateViewMarkerDisc> getStateViewMarkerDiscList() {
        return stateViewMarkerDiscList;
    }
}
