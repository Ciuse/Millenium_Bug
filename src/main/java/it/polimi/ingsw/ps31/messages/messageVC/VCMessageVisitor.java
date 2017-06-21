package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.controller.Controller;
import it.polimi.ingsw.ps31.messages.GenericMessage;

/**
 * Created by Giuseppe on 12/06/2017.
 */
public class VCMessageVisitor extends GenericMessage implements VCVisitor {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void visit(VCLeaderChoice vcLeaderChoice) {
        controller.createLeader(vcLeaderChoice.getLeaderId());
    }
}
