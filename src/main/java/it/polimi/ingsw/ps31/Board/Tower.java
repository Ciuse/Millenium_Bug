package it.polimi.ingsw.ps31.Board;

import it.polimi.ingsw.ps31.Card.DevelopmentCardDeck;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Effect.EffectList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class Tower {

    private final int TOWERDIMENSION; //Number of floors in the tower

    private final CardColor color;
    private boolean isOccupied = false;
    private final TowerCardSpace[] towerCardSpaceList;
    private final ActionSpace[] actionSpaceList;
    private DevelopmentCardDeck deck;

    /* Constructor */
    public Tower(int towerDimension, CardColor color, List<EffectList> effectList)
    {
        this.TOWERDIMENSION = towerDimension;
        this.towerCardSpaceList = new TowerCardSpace[TOWERDIMENSION];
        this.actionSpaceList = new ActionSpace[TOWERDIMENSION];
        for (int i = 0; i<TOWERDIMENSION; i++)
        {
            int[] diceCostList= {1,3,5,7};
            towerCardSpaceList[i] = new TowerCardSpace(color, null, this);   //TODO: inserire parametro dell'action space
            actionSpaceList[i] = new ActionSpace(diceCostList[i],1,effectList.get(i));
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

    public TowerCardSpace[] getTowerCardSpaceList()
    {
        return towerCardSpaceList;
    }

    public ActionSpace[] getActionSpaceList()
    {
        return actionSpaceList;
    }

    public TowerCardSpace getCardBoxListAt(int i)
    {
        return towerCardSpaceList[i];
    }

    public ActionSpace getActionBoxListAt(int i)
    {
        return actionSpaceList[i];
    }

    public void setDeck(List<DevelopmentCardDeck> deckList, int period) {
        for(int i=0; i<deckList.size();i++){
            if(deckList.get(i).getPeriod()==period&&deckList.get(i).getColor().equals(this.color))
                this.deck=deckList.get(i);
        }
    }

    public void drawCardFromDeck(){
        for(int cardSpaceNum=0;cardSpaceNum<=TOWERDIMENSION;cardSpaceNum++) {
            this.towerCardSpaceList[cardSpaceNum].setCard(deck.draw());
        }

    }

    public void drawFromDeck(int period){

    }



}