package it.polimi.ingsw.ps31;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class Card {
    private final String name;

    public Card(String name) {
        this.name = name;
    }

    public Card() {
        this.name=null;
    }

    public String getName (){
        return this.name;
    }


}
