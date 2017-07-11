package it.polimi.ingsw.ps31.messages.messageVC;


/**
 * Created by Giuseppe on 12/06/2017.
 *
 * Interfaccia del pattern Visitor (dei messaggi di scelta), implementata dall'oggetto visitatore che sa come visitare i vari oggetti diversi
 * @see VCMessageVisitor
 */
public interface VCVisitor {
    void visit(VCStartLeaderChoice vcStartLeaderChoice);

    void visit(VCActiveEffectChoice vcActiveEffectChoice);

    void visit(VCPlayerAction vcPlayerAction);

    void visit(VCActionSpace vcActionSpace);

    void visit(VCColorChoice vcColorChoice);

    void visit(VCFamilyMemberChoice vcFamilyMemberChoice);

    void visit(VCSupportTheChurchChoice vcSupportTheChurchChoice);

    void visit(VCLeaderToActiveChoice vcLeaderToActiveChoice);

    void visit(VCListToPayChoice vcListToPayChoice);

    void visit(VCServantToPayChoice vcServantToPayChoice);

    void visit(VCTowerCardSpaceChoice vcTowerCardSpaceChoice);

    void visit(VCPersonalTilesChoice vcPersonalTilesChoice);

    void visit(VCCouncilPrivilegeChoice vcCouncilPrivilegeChoice);

    void visit(VCLeaderToDiscardChoice vcLeaderToDiscardChoice);

    void visit(VCLeaderToCopyChoice vcLeaderToCopyChoice);
}
