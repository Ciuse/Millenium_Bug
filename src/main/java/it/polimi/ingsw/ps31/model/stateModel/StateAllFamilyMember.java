package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.List;

/**
 * Created by giulia on 08/06/2017.
 */
public class StateAllFamilyMember extends StateType {
    private final List<StateFamilyMember> stateAllFamilyMember;

    public StateAllFamilyMember(List<StateFamilyMember> stateAllFamilyMember) {
        this.stateAllFamilyMember = stateAllFamilyMember;
    }

    public List<StateFamilyMember> getStateAllFamilyMember() {
        return stateAllFamilyMember;
    }

    public PlayerId getIdFamilyMemberList() {
        return stateAllFamilyMember.get(0).getPlayerId();
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
