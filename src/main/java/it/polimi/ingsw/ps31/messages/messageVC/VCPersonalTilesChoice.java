package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 25/06/2017.
 */
public class VCPersonalTilesChoice extends VCVisitable {
    private final int personalBonusChoice;

    public VCPersonalTilesChoice(PlayerId viewId, int personalBonusChoice) {
        super(viewId);
        this.personalBonusChoice = personalBonusChoice;
    }

    public int getPersonalBonusChoice() {
        return personalBonusChoice;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
