package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 31/05/2017.
 */
public class TakeDevelopmentCardControl extends Control {
    private DevelopmentCard developmentCard = null;

    /* Constructor */
    public TakeDevelopmentCardControl(Player player) {
        super(player);
    }

    /* Setters & Getters */
    public void setDevelopmentCard(DevelopmentCard developmentCard)
    {
        this.developmentCard = developmentCard;
    }

    public DevelopmentCard getDevelopmentCard()
    {
        return this.developmentCard;
    }

    /* Resetters */
    public void resetDevelopmentCard()
    {
        this.developmentCard = null;
    }

    /* Execution Method */
    @Override
    public boolean execute() {
        //Controllo che i parametri siano settati
        if( this.developmentCard == null )
        {
            //todo eccezione
            return false;
        }

        //Controllo che il player possa pagare il costo della carda
        boolean costAffordable = false;
        for (ResourceList currentCost :  developmentCard.getCostList())
            if( currentCost.lessOrEquals(player.getPlayerResources().getPlayerResourceList()) )
                costAffordable = true;

        if (! costAffordable)
        {
            //todo eccezione?
            return false;
        }

        //Controllo che il player abbia spazio nella personal board per la carta in questione
        //Controllo spazio residuo
//        if(player.getPlayerBoard().)
        //Controllo requisiti
//        if()  todo robe di giuse needed
        return true;
    }
}
