package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.StateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.board.PhysicalCardBox;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;

/**
 * Created by Francesco on 12/05/2017.
 */
public class PersonalBoardCardCell extends PhysicalCardBox {
    private final int value;
    private final PointResource extraValue;

    public PersonalBoardCardCell(int value, PointResource extraValue) {
        super();
        this.value = value;
        this.extraValue = extraValue;
    }

    public int getValue() {
        return value;
    }

    public PointResource getExtraValue() {
        return extraValue;
    }

    @Override
    public StateCardBox GetStateCardBox(){
        if(this.card!=null) {
            StateCardBox stateCardBox = new StateCardBox(super.card.getName(), super.card.getCardId(), super.card.getCardColor(),this.value,this.extraValue);
            return stateCardBox;
        }
        return null;
    }
}
