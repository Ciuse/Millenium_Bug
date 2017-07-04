package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.board.PhysicalCardBox;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.PointResource;
import it.polimi.ingsw.ps31.model.stateModel.StateCardBox;

/**
 * Created by Francesco on 12/05/2017.
 *
 * Classe che rappresenta la singola casella della personal board, la quale estente CardBox fisico che contiene la carta
 * @see PhysicalCardBox
 * @see PersonalBoardCardList
 */
public class PersonalBoardCardCell extends PhysicalCardBox {
    private PlayerId playerId;
    private final int value;
    private final PointResource extraPointRequired; // sono i requisiti per poter mettere una carta sulla personal board
    private final Model model;

    public PersonalBoardCardCell(PlayerId playerId, int value, PointResource extraPointRequired, Model model) {
        super();
        this.model=model;
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

    @Override
    public void setCard(DevelopmentCard card) {
        super.setCard(card);
        model.notifyViews(new MVUpdateState("Aggiornato stato del personal card box", getStatePersonalCardBox()));

    }

    public StateCardBox getStatePersonalCardBox(){
        if(this.card!=null) {

            return new StateCardBox(playerId,super.card.getName(), super.card.getCardId(), super.card.getCardColor(),this.value,this.extraPointRequired.getValue());
        }
        return null;
    }
}
