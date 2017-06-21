package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 28/05/2017.
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

    public List<ResourceList> getResourceLists()
    {
        return resourceLists;
    }

    /* Resetters */
    public void restResourceList()
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
            //TODO: gestire;
            result = false;
        } else
        {
            Iterator<ResourceList> itr = this.resourceLists.iterator();
            boolean foundError = false;
            while (itr.hasNext() && !foundError) {
                ResourceList currentResourceList = itr.next();

                super.setResourceList(currentResourceList);
                foundError = !super.execute();
            }

            result = !foundError;
        }

        resetResourceList();
        return result;

    }
}
