package it.polimi.ingsw.ps31.model.player;

/**
 * Created by Francesco on 28/05/2017.
 */
public abstract class HarvestProductionList{
    private final Player player;

    /* Constructor */
    public HarvestProductionList(Player player) {

        this.player = player;
    }

    /* Getters & Setters*/
    protected Player getPlayer()
    {
        return this.player;
    }

    /* Class Methods */
    public abstract void activate(int diceValue);


}
