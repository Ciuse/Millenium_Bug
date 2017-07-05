package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by Giuseppe on 04/07/2017.
 * Stato che rappresenta una generica scomunica e nel caso il player che la possiede
 *
 * @see StateType
 * @see it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState
 * @see it.polimi.ingsw.ps31.model.card.ExcommunicationTiles
 *
 */
public class StateExcommunication extends StateType {
    private int id;
    private final int period;
    private final String bonusName;
    private PlayerId playerId=null;

    public StateExcommunication(int id,int period, String bonusName) {
        this.id=id;
        this.period = period;
        this.bonusName = bonusName;
    }

    public StateExcommunication(int id,int period, String bonusName, PlayerId playerId) {
        this.id=id;
        this.period = period;
        this.bonusName = bonusName;
        this.playerId=playerId;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }

    public int getPeriod() {
        return period;
    }

    public String getBonusName() {
        return bonusName;
    }

    public int getId() {
        return id;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }
}
