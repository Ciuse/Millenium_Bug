package it.polimi.ingsw.ps31.model.player;

/**
 * Created by Francesco on 28/05/2017.
 *
 * Classe padre delle liste degli effetti produzione e raccolto del player
 * Entrambe necessitano di un riferimento al Player a cui appartengono per attivare gli effetti
 * su di lui e del metodo activate
 */
public abstract class HarvestProductionList{
    protected final Player player;

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
