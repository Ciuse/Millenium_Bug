package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 23/06/2017.
 */
public class VCActionSpace extends VCVisitable {
    private final int actionSpaceId;

    public VCActionSpace(PlayerId viewId, int actionSpaceId) {
        super(viewId);
        this.actionSpaceId = actionSpaceId;
    }

    public int getActionSpaceId() {
        return actionSpaceId;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
