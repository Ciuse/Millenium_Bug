package it.polimi.ingsw.ps31.model.board;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.card.DevelopmentCardDeck;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.stateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.stateModel.StateTower;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 12/05/2017.
 */
public class Tower {

    private static final int TOWERDIMENSION=4; //Number of floors in the tower

    private final CardColor color;
    private boolean isOccupied = false;
    private final List<TowerCardSpace> towerCardSpaceList;
    private DevelopmentCardDeck deck;
    private final Model model;

    /* Constructor */
    public Tower(CardColor color, List<EffectList> effectList, Model model)
    {
        this.model = model;

        this.towerCardSpaceList = new ArrayList<>();
        for (int i = 0; i<TOWERDIMENSION; i++)
        {
            int[] diceCostList= {1,3,5,7};
            ActionSpace actionSpace= new ActionSpace(diceCostList[i],1, effectList.get(i));
            towerCardSpaceList.add(new TowerCardSpace(color, actionSpace, this, i));
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

    public List<TowerCardSpace> getTowerCardSpaceList() {
        return new ArrayList<>(towerCardSpaceList);
    }

    public Model getModel() {
        return model;
    }

    public void setDeck(List<DevelopmentCardDeck> deckList, int period) {
        for(int i=0; i<deckList.size();i++){
            if(deckList.get(i).getPeriod()==period&&deckList.get(i).getColor().equals(this.color))
                this.deck=deckList.get(i);
        }
    }

    public void drawCardFromDeck(){
        for(int cardSpaceNum=0;cardSpaceNum<=TOWERDIMENSION;cardSpaceNum++) {
            towerCardSpaceList.get(cardSpaceNum).setCard(deck.draw());
        }

    }

    public StateTower getStateTower(){
        List<StateCardBox> stateCardBoxes = new ArrayList<>();
        for (TowerCardSpace towerCardSpace : towerCardSpaceList
                ) {
            stateCardBoxes.add(towerCardSpace.getStateTowerCardBox());
        }
        return new StateTower(color,stateCardBoxes);
    }
}