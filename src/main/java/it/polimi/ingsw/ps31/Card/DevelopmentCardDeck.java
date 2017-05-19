package it.polimi.ingsw.ps31.Card;

import it.polimi.ingsw.ps31.Constants.CardColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 13/05/2017.
 */
public class DevelopmentCardDeck extends Deck {
        private final CardColor cardColor;
        private int maxNumber =8;
        private final List<DevelopmentCard> cardList = new ArrayList<>();

    public DevelopmentCardDeck(CardColor cardColor, List<Card> cardList) {
            this.cardColor = cardColor;
        }

    public void setCard(DevelopmentCard card){
        this.cardList.add(card);
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
