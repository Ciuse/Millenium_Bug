package it.polimi.ingsw.ps31.Card;

import it.polimi.ingsw.ps31.Constants.CardColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 13/05/2017.
 */
public class DevelopmentCardDeck extends Deck {
    private final CardColor cardColor;
    private final int period;
    private int maxNumber =8;
    private final List<DevelopmentCard> cardList = new ArrayList<>(maxNumber);


    public DevelopmentCardDeck(CardColor cardColor, int period) {
            this.cardColor = cardColor;
            this.period = period;
    }

    public void setCard(DevelopmentCard card){
        this.cardList.add(card);
    }

    public int getPeriod() {
        return period;
    }

    public CardColor getColor(){
        return this.cardColor;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getCardListSize(){
        return this.cardList.size();
    }

    public DevelopmentCard draw(){
        if(maxNumber>1) {           //le carte vanno dalla posizione 0 a 7
            maxNumber--;
            DevelopmentCard cardDraw = this.cardList.get(maxNumber);
            this.cardList.remove(cardDraw);
            return cardDraw;
        }
        return null;
    }

}
