package it.polimi.ingsw.ps31.messages.messageVC;

/**
 * Created by Giuseppe on 21/06/2017.
 */
public class VCActiveEffectChoice implements VCVisitable {
    private boolean toActive;

    public VCActiveEffectChoice(boolean toActive) {
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
