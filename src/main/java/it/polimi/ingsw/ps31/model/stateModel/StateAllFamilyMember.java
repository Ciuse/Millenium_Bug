package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.List;

/**
 * Created by giulia on 08/06/2017.
 * Stato che raccoglie tutti gli stati dei family member per ottimizzarne l invio quando è necessario aggiornarli tutti
 *
 * @see StateType
 * @see it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState
 * @see StateFamilyMember
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
