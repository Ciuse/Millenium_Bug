package it.polimi.ingsw.ps31.Board;


import it.polimi.ingsw.ps31.Card.DevelopmentCard;
import it.polimi.ingsw.ps31.Constants.CardColor;

/**
 * Created by Francesco on 12/05/2017.
 */
public class TowerCardSpace extends PhysicalCardBox {

    private final CardColor color;
    private DevelopmentCard card;

    public TowerCardSpace(CardColor color) {
        this.color = color;
        this.card = null;
    }

    /*Getters & Setters*/
    public CardColor getColor()
    {
        return this.color;
    }

    public DevelopmentCard getCard ()
    {
        return this.card;
    }

    public void setCard(DevelopmentCard card)
    {
        if (card.getCardColor() != this.color)
        {
            //TODO: gestire (eccezione?)
        }
        else
            this.card = card;
    }
}
