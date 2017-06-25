package it.polimi.ingsw.ps31.messages.messageVC;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 25/06/2017.
 */
public class VCTowerCardSpaceChoice extends VCVisitable {
    private final CardColor towerColor;
    private final int floorNumber;

    public VCTowerCardSpaceChoice(PlayerId viewId, CardColor towerColor, int floorNumber) {
        super(viewId);
        this.towerColor = towerColor;
        this.floorNumber = floorNumber;
    }

    public CardColor getTowerColor() {
        return towerColor;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    @Override
    public void accept(VCVisitor vcVisitor) {
        vcVisitor.visit(this);
    }
}
