package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class VCServantToPayChoice extends VCVisitable {
    private final int servantToPay;

    public VCServantToPayChoice(PlayerId viewId, int servantToPay) {
        super(viewId);
        this.servantToPay = servantToPay;
    }

    public int getServantToPay() {
        return servantToPay;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
