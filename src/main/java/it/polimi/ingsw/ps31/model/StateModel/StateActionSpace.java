package it.polimi.ingsw.ps31.model.StateModel;

import java.util.List;

/**
 * Created by giulia on 11/06/2017.
 */
public class StateActionSpace extends StateInfo{
    private int numberOfActionSpace=-1;
    private int diceValue =0;
    private final List<StateFamilyMember> stateFamilyMemberList;

    public StateActionSpace(int numberOfActionSpace, List<StateFamilyMember> stateFamilyMemberList) {
        this.numberOfActionSpace = numberOfActionSpace;
        this.stateFamilyMemberList = stateFamilyMemberList;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }


    public int getNumberOfActionSpace() {
        return numberOfActionSpace;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public List<StateFamilyMember> getStateFamilyMemberList() {
        return stateFamilyMemberList;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
