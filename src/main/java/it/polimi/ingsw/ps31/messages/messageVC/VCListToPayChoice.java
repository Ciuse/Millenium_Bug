package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class VCListToPayChoice extends VCVisitable {
    private final int listChoice;

    public VCListToPayChoice(PlayerId viewId, int listChoice) {
        super(viewId);
        this.listChoice = listChoice;
    }

    public int getListChoice() {
        return listChoice;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
