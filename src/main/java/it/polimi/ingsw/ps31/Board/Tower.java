package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.*;

/**
 * Created by Francesco on 12/05/2017.
 */
public class Tower {

    private final int TOWERDIMENSION; //Number of floors in the tower

    private final CardColor color;
    private boolean isOccupied = false;
    private final TowerCardSpace[] cardBox;
    private final ActionSpace[] actionBox;

    /* Constructor */
    public Tower(int towerDimension, CardColor color)
    {
        this.TOWERDIMENSION = towerDimension;
        this.cardBox = new TowerCardSpace[TOWERDIMENSION];
        this.actionBox = new ActionSpace[TOWERDIMENSION];
        for (int i = 0; i<TOWERDIMENSION; i++)
        {
            cardBox[i] = new TowerCardSpace(color);
            actionBox[i] = new ActionSpace(0,1,null); //TODO: leggere parametri da file
        }

        this.color = color;
    }

    /* Getters & Setters */
    public CardColor getColor()
    {
        return color;
    }

    public boolean isOccupied()
    {
        return isOccupied;
    }

    public void setOccupied(boolean occupied)
    {
        isOccupied = occupied;
    }

    public TowerCardSpace[] getCardBox()
    {
        return cardBox;
    }

    public ActionSpace[] getActionBox()
    {
        return actionBox;
    }

    public TowerCardSpace getCardBoxAt(int i)
    {
        return cardBox[i];
    }

    public ActionSpace getActionBoxAt(int i)
    {
        return actionBox[i];
    }


}