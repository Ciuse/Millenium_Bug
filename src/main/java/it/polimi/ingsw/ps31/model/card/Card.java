package it.polimi.ingsw.ps31.model.card;

/**
 * Created by Giuseppe on 09/05/2017.
 *
 * Classe astratta che rappresenta una generica carta la quale ha un nome
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
