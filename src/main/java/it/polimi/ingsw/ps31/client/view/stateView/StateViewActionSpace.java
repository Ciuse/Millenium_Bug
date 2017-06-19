package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.stateModel.StateActionSpace;
import it.polimi.ingsw.ps31.model.stateModel.StateFamilyMember;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giulia on 11/06/2017.
 */
public class StateViewActionSpace {
    private final int numberOfActionSpace;
    private int diceValue;
    private final StateViewEffect stateViewEffect;
    private List<StateFamilyMember> stateFamilyMemberList = new ArrayList<>();

    public StateViewActionSpace(int numberOfActionSpace, StateViewEffect stateViewEffect,int diceValue) {
        this.numberOfActionSpace = numberOfActionSpace;
        this.stateViewEffect=stateViewEffect;
        this.diceValue=diceValue;
        this.stateFamilyMemberList=null;
    }


    public void updateState(StateActionSpace stateActionSpace){
        if(stateActionSpace.getNumberOfActionSpace()!= -1){
            if(stateActionSpace.getNumberOfActionSpace()==numberOfActionSpace){
                this.stateFamilyMemberList=stateActionSpace.getStateFamilyMemberList();
            }
        }
    }

    public int getNumberOfActionSpace() {
        return numberOfActionSpace;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public StateViewEffect getStateViewEffect() {
        return stateViewEffect;
    }

    public List<StateFamilyMember>  getStateFamilyMemberList() {
        return stateFamilyMemberList;
    }
}
