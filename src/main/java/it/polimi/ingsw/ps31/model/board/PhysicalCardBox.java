package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.player.PersonalBoardCardCell;

/**
 * Created by Francesco on 12/05/2017.
 *
 * Classe astratta che rappresenta una casella che contenere fisicamente una carta
 * @see PersonalBoardCardCell
 * @see TowerCardSpace
 */
public abstract class PhysicalCardBox {
    protected DevelopmentCard card;

    /* Constructor */
    public PhysicalCardBox()
    {
        this.card = null;
    }

    /* Getters & Setters */
    public void setCard(DevelopmentCard card)
    {
        this.card = card;
    }

    /**
     * Prende la propria carta, per poi settarsi il valore a null, per poi ritornarla
     * @return ritorna la carta presa
     */
    public DevelopmentCard takeCard()
    {
        DevelopmentCard takenCard = this.card;
        this.card = null;
        return takenCard;
    }

    /* Class Methods */
    public DevelopmentCard getCard()
    {
        return this.card;
    }


}
