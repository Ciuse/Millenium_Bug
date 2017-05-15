package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.CardColor;

/**
 * Created by Francesco on 12/05/2017.
 */
public class TowerCardSpace extends PhysicalCardBox {

    private final CardColor color;

    public TowerCardSpace(CardColor color) {
        this.color = color;
    }

    /*Getters & Setters*/
    public CardColor getColor()
    {
        return this.color;
    }
}
