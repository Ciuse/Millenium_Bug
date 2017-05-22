package it.polimi.ingsw.ps31.Card;


import it.polimi.ingsw.ps31.Constants.CardColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 22/05/2017.
 */
public class DevelopmentCardList {
    private List<DevelopmentCard> developmentCardList = new ArrayList<>();
    private final CardColor cardColor;
    public DevelopmentCardList(CardColor cardColor){

        this.cardColor = cardColor;
    }

    public void addDevelopmentCard(DevelopmentCard cardToAdd){
        this.developmentCardList.add(cardToAdd);
    }

    public List<DevelopmentCard> getDevelopmentCardList() {
        return new ArrayList<>(this.developmentCardList);
    }

}
