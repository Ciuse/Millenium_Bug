package it.polimi.ingsw.ps31.Card;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class Card {
    private final String name;

    public Card(String name) {
        this.name = name;
    }

    /*Getters*/
    public String getName (){
        return this.name;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName()+"["+this.getName()+"]";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        return name != null ? name.equals(card.name) : card.name == null;
    }
    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
