package it.polimi.ingsw.ps31.model.StateModel;

import java.util.List;

/**
 * Created by giulia on 11/06/2017.
 */
public class StateActionSpace {
    private int numberOfActionSpace=-1;
    private int diceValue =0;
    private String stringEffect= null;
    private final List<StateFamilyMember> stateFamilyMemberList;

    public StateActionSpace(int numberOfActionSpace, List<StateFamilyMember> stateFamilyMemberList) {
        this.numberOfActionSpace = numberOfActionSpace;
        this.stateFamilyMemberList = stateFamilyMemberList;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public void setStringEffect(String stringEffect) {
        this.stringEffect = stringEffect;
    }

    public int getNumberOfActionSpace() {
        return numberOfActionSpace;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public String getStringEffect() {
        return stringEffect;
    }

    public List<StateFamilyMember> getStateFamilyMemberList() {
        return stateFamilyMemberList;
    }
}
