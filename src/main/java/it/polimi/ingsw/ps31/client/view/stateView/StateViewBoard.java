package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateActionSpace;
import it.polimi.ingsw.ps31.model.StateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.StateModel.StateMarkerDisc;
import it.polimi.ingsw.ps31.model.StateModel.StateTower;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.*;

/**
 * Created by giulia on 07/06/2017.
 */
public class StateViewBoard {
    private final List<StateViewTower> stateViewTowerList = new ArrayList<>();
    private final List<StateViewActionSpace> stateViewActionSpaceList=new ArrayList<>();

    public StateViewBoard() {
        for(int i = 0;i<getNumber_Of_Tower();i++){
            stateViewTowerList.add(new StateViewTower(getCardColors()[i]));
        }
        for(int i=1;i<=getNumber_Of_ActionSpace();i++){
            stateViewActionSpaceList.add(new StateViewActionSpace(i,getActionSpaceEffect()[i],getDiceActionSpaceValue()[i]));
        }
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



    public List<StateViewTower> getStateViewTowerList() {
        return stateViewTowerList;
    }

    public List<StateViewActionSpace> getStateViewActionSpaceList() {
        return stateViewActionSpaceList;
    }

}
