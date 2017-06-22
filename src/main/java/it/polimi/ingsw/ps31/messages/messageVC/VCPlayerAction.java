package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 21/06/2017.
 */
public class VCPlayerAction extends VCVisitable {
    private String actionName;

    public VCPlayerAction(PlayerId viewId, String actionName) {
        super(viewId);
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
