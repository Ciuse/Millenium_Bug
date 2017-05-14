package it.polimi.ingsw.ps31.Card;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class Card {
    private final String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName (){
        return this.name;
    }


}
