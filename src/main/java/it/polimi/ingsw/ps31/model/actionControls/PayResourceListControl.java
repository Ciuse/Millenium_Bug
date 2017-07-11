package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 28/05/2017.
 *
 * Controllo se è possibile pagare Almeno una delle resource List presente nella lista di costi
 */
public class  PayResourceListControl extends PayResourceControl{
    private List<ResourceList> resourceLists = null;

    /* Constructor */
    public PayResourceListControl(Player player)
    {
        super(player);
    }

    /* Setters & Getters */
    public void setResourceLists(List<ResourceList> resourceLists)
    {
        this.resourceLists = resourceLists;
    }

    @Override
    public String getControlStringError() {
        return "Non puoi pagare nessuna delle liste di costo";
    }

    /* Resetters */
    public void resetResourceList()
    {
        this.resourceLists = null;
    }




    /* Class Methods */
    @Override
    public boolean execute()
    {
        boolean result;
        //Controllo che i parametri siano settati
        if ( this.resourceLists == null )
        {
            result = false;
        } else
        {
            Iterator<ResourceList> itr = this.resourceLists.iterator();
            boolean canPayCost = true;
            while (itr.hasNext() && canPayCost)
            {
                ResourceList currentResourceList = itr.next();
                super.setResourceList(currentResourceList);
                canPayCost = super.execute();
            }
            result = canPayCost;
        }

        resetResourceList();
        return result;
    }
}
