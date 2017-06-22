package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 20/06/2017.
 */
public class VCStartLeaderChoice extends VCVisitable {
    private int leaderId;

    public VCStartLeaderChoice(PlayerId viewId,int leaderId) {
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
