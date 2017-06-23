package it.polimi.ingsw.ps31.messages.messageVC;

/**
 * Created by Giuseppe on 12/06/2017.
 */
public interface VCVisitor {
    void visit(VCStartLeaderChoice vcStartLeaderChoice);
    void visit(VCActiveEffectChoice vcActiveEffectChoice);
    void visit(VCPlayerAction vcPlayerAction);
    void visit(VCActionSpace vcActionSpace);
    void visit(VCColorChoice vcColorChoice);
    void visit(VCFamilyMemberChoice vcFamilyMemberChoice);
}
