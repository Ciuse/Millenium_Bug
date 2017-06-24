package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class VCSupportTheChurchChoice extends VCVisitable {
    private final boolean wannaSupport;

    public VCSupportTheChurchChoice(PlayerId viewId, boolean wannaSupport) {
        super(viewId);
        this.wannaSupport = wannaSupport;
    }

    public boolean isWannaSupport() {
        return wannaSupport;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
