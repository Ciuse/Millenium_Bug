package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class VCLeaderToDiscard extends VCVisitable {
    private final int leaderId;

    public VCLeaderToDiscard(PlayerId viewId, int leaderId) {
        super(viewId);
        this.leaderId = leaderId;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {

    }
}
