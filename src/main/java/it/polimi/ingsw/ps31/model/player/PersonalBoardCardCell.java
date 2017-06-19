package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.stateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.board.PhysicalCardBox;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;

/**
 * Created by Francesco on 12/05/2017.
 */
public class PersonalBoardCardCell extends PhysicalCardBox {
    private PlayerId playerId;
    private final int value;
    private final PointResource extraValue;

    public PersonalBoardCardCell(PlayerId playerId, int value, PointResource extraValue) {
        super();
        this.playerId=playerId;
        this.value = value;
        this.extraValue = extraValue;
    }

    public int getValue() {
        return value;
    }

    public PointResource getExtraValue() {
        return extraValue;
    }


    public StateCardBox getStatePersonalCardBox(){
        if(this.card!=null) {
            StateCardBox stateCardBox = new StateCardBox(playerId,super.card.getName(), super.card.getCardId(), super.card.getCardColor(),this.value,this.extraValue.getValue());
            return stateCardBox;
        }
        return null;
    }
}
