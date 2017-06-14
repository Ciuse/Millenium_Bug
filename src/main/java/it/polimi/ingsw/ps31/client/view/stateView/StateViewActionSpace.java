package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateActionSpace;
import it.polimi.ingsw.ps31.model.StateModel.StateEffect;
import it.polimi.ingsw.ps31.model.StateModel.StateFamilyMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giulia on 11/06/2017.
 */
public class StateViewActionSpace {
    private final int numberOfActionSpace;
    private int diceValue;
    private StateEffect stateEffect;
    private List<StateFamilyMember> stateFamilyMemberList = new ArrayList<>();

    public StateViewActionSpace(int numberOfActionSpace) {
        this.numberOfActionSpace = numberOfActionSpace;
    }


    public void updateState(StateActionSpace stateActionSpace){
        if(stateActionSpace.getNumberOfActionSpace()!= -1){
            if(stateActionSpace.getNumberOfActionSpace()==numberOfActionSpace){
                this.stateFamilyMemberList=stateActionSpace.getStateFamilyMemberList();
            }
            if(stateActionSpace.getStateEffect()!=null){
                this.stateEffect = stateActionSpace.getStateEffect();
            }
            if(stateActionSpace.getDiceValue()!=0){
                this.diceValue = stateActionSpace.getDiceValue();
            }
        }
    }

    public int getNumberOfActionSpace() {
        return numberOfActionSpace;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public StateEffect getStateEffect() {
        return stateEffect;
    }

    public List<StateFamilyMember> getStateFamilyMemberList() {
        return stateFamilyMemberList;
    }
}
