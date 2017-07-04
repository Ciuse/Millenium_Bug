package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class VCLeaderToDiscardChoice extends VCVisitable {
    private final int leaderId;

    public VCLeaderToDiscardChoice(PlayerId viewId, int leaderId) {
        super(viewId);
        this.leaderId = leaderId;
    }

    public int getLeaderId() {
        return leaderId;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
