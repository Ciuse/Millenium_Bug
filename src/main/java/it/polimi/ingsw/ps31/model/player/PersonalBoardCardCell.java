package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.board.PhysicalCardBox;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;
import it.polimi.ingsw.ps31.model.stateModel.StateCardBox;

/**
 * Created by Francesco on 12/05/2017.
 */
public class PersonalBoardCardCell extends PhysicalCardBox {
    private PlayerId playerId;
    private final int value;
    private final PointResource extraPointRequired; // sono i requisiti per poter mettere una carta sulla personal board

    public PersonalBoardCardCell(PlayerId playerId, int value, PointResource extraPointRequired) {
        super();
        this.playerId=playerId;
        this.value = value;
        this.extraPointRequired = extraPointRequired;
    }

    public int getValue() {
        return value;
    }

    public PointResource getExtraPointRequired() {
        return extraPointRequired;
    }


    public StateCardBox getStatePersonalCardBox(){
        if(this.card!=null) {
            StateCardBox stateCardBox = new StateCardBox(playerId,super.card.getName(), super.card.getCardId(), super.card.getCardColor(),this.value,this.extraPointRequired.getValue());
            return stateCardBox;
        }
        return null;
    }
}
