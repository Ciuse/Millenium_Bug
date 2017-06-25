package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.controller.Controller;
import it.polimi.ingsw.ps31.messages.GenericMessage;

/**
 * Created by Giuseppe on 12/06/2017.
 */
public class VCMessageVisitor implements VCVisitor {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void visit(VCStartLeaderChoice vcStartLeaderChoice) {
        controller.createLeader(vcStartLeaderChoice.getLeaderId(),vcStartLeaderChoice.getViewId());
    }

    @Override
    public void visit(VCActiveEffectChoice vcActiveEffectChoice) {
        controller.activeEffect(vcActiveEffectChoice.isToActive(),vcActiveEffectChoice.getViewId());
    }

    @Override
    public void visit(VCPlayerAction vcPlayerAction) {
        controller.doPlayerAction(vcPlayerAction.getActionName(),vcPlayerAction.getViewId());
    }

    @Override
    public void visit(VCActionSpace vcActionSpace) {
        controller.selectActionSpace(vcActionSpace.getActionSpaceId(),vcActionSpace.getViewId());
    }

    @Override
    public void visit(VCColorChoice vcColorChoice) {
        controller.selectColor(vcColorChoice.getPlayerColor(),vcColorChoice.getViewId());
    }

    @Override
    public void visit(VCFamilyMemberChoice vcFamilyMemberChoice) {
        controller.selectFamilyMember(vcFamilyMemberChoice.getFamilyColor(),vcFamilyMemberChoice.getViewId());
    }

    @Override
    public void visit(VCSupportTheChurchChoice vcSupportTheChurchChoice) {
        controller.selectIfSupportTheChurch(vcSupportTheChurchChoice.isWannaSupport(),vcSupportTheChurchChoice.getViewId());
    }

    @Override
    public void visit(VCLeaderToActiveChoice vcLeaderToActiveChoice) {
        controller.selectLeaderToActivate(vcLeaderToActiveChoice.getLeaderId(),vcLeaderToActiveChoice.getViewId());
    }

    @Override
    public void visit(VCListToPayChoice vcListToPayChoice) {
        controller.selectListToPay(vcListToPayChoice.getListChoice(),vcListToPayChoice.getViewId());
    }

    @Override
    public void visit(VCServantToPayChoice vcServantToPayChoice) {
        controller.selectServantToPay(vcServantToPayChoice.getServantToPay(),vcServantToPayChoice.getViewId());
    }


}
