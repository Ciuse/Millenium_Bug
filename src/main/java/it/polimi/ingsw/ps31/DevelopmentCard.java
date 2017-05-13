package it.polimi.ingsw.ps31;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class DevelopmentCard extends Card {

    private final CardColor cardColor;
    private final Period period;
    private final List<Resource> cost;

    public DevelopmentCard(String name, CardColor cardColor, Period period, List<Resource> cost) {
        super(name);
        this.cardColor = cardColor;
        this.period = period;
        this.cost = cost;
    }

    public DevelopmentCard(){
        super();
        this.cardColor=null;
        this.period=null;
        this.cost=null;
    }


    public String toString() {
        return "["+this.getName()+"]";
    }

    public CardColor getCardColor(){
        return this.cardColor;
    }
    public Period getPeriod(){
        return this.period;
    }
    public List<Resource> getCost(){
        return this.cost;
    }


}
