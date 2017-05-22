package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class GetResources extends Actions{



    private ResourceList resourcesToGet = null;

    public GetResources(Player player)
    {
        super(player);
    }

    public void setResourcesToGet (ResourceList resourcesToGet)
    {
        this.resourcesToGet = resourcesToGet;
    }

    public ResourceList getResourcesToGet()
    {
        return this.resourcesToGet;
    }

    @Override
    public void activate() {
        if (resourcesToGet == null)
        {
            //TODO: fare qualcosa (eccezione?)
        }

        //player.addResources(this.resourcesToGet);
        //this.resourcesToGet = null;
    }
}
