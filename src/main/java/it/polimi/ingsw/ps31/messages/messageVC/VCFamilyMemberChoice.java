package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class VCFamilyMemberChoice extends VCVisitable {
    private final DiceColor familyColor;

    public VCFamilyMemberChoice(PlayerId viewId, DiceColor familyColor) {
        super(viewId);
        this.familyColor = familyColor;
    }

    public DiceColor getFamilyColor() {
        return familyColor;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
