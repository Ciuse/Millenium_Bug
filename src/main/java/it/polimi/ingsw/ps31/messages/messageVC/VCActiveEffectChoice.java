package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 21/06/2017.
 */
public class VCActiveEffectChoice extends VCVisitable {
    private boolean toActive;

    public VCActiveEffectChoice(PlayerId viewId, boolean toActive) {
        super(viewId);
        this.toActive = toActive;
    }

    public boolean isToActive() {
        return toActive;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
