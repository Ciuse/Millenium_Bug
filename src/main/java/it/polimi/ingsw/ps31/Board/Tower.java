package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.*;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.Effect.EffectList;

import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class Tower {

    private final int TOWERDIMENSION; //Number of floors in the tower

    private final CardColor color;
    private boolean isOccupied = false;
    private final TowerCardSpace[] cardBoxList;
    private final ActionSpace[] actionBoxList;

    /* Constructor */
    public Tower(int towerDimension, CardColor color, List<EffectList> effectList)
    {
        this.TOWERDIMENSION = towerDimension;
        this.cardBoxList = new TowerCardSpace[TOWERDIMENSION];
        this.actionBoxList = new ActionSpace[TOWERDIMENSION];
        for (int i = 0; i<TOWERDIMENSION; i++)
        {
            int[] diceCostList= {1,3,5,7};
            cardBoxList[i] = new TowerCardSpace(color, null);   //TODO: inserire parametro dell'action space
            actionBoxList[i] = new ActionSpace(diceCostList[i],1,effectList.get(i));
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

    public TowerCardSpace[] getCardBoxList()
    {
        return cardBoxList;
    }

    public ActionSpace[] getActionBoxList()
    {
        return actionBoxList;
    }

    public TowerCardSpace getCardBoxListAt(int i)
    {
        return cardBoxList[i];
    }

    public ActionSpace getActionBoxListAt(int i)
    {
        return actionBoxList[i];
    }


}