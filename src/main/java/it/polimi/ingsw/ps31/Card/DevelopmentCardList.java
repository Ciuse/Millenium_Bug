package it.polimi.ingsw.ps31.Card;


import it.polimi.ingsw.ps31.Constants.CardColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 22/05/2017.
 */
public class DevelopmentCardList {
    //TODO------------------------------------------------------
    //TODO:Tutta la classe è sbagliata, serve solo per essere compilata => usare quella che scriverà giuse
    //TODO------------------------------------------------------

    private List<DevelopmentCard> developmentCardList = new ArrayList<>();
    private final CardColor cardColor;
    private int size = 0;

    public DevelopmentCardList(CardColor cardColor){

        this.cardColor = cardColor;
    }

    public void addDevelopmentCard(DevelopmentCard cardToAdd){
        this.developmentCardList.add(cardToAdd);
    }

    public List<DevelopmentCard> getDevelopmentCardList() {
        return new ArrayList<>(this.developmentCardList);
    }

    public DevelopmentCardList getSpecificCardList(CardColor cardColor)
    {
        return this;
    }

    public int size()
    {
        return this.size;
    }
}
