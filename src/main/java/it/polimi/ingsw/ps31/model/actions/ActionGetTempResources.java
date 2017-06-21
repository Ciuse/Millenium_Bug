package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giulia on 15/06/2017.
 */
public class ActionGetTempResources extends Action {
    private ResourceList resourcesTempToGet = null;
    private ResourceList resourceBonus = new ResourceList();
    private List<ResourceList> resourceBonusChoice = new ArrayList<>();

    /* Constructor */
    public ActionGetTempResources(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
    }

    public ResourceList getResourcesTempToGet() {
        return resourcesTempToGet;
    }

    public void setResourcesTempToGet(ResourceList resourcesTempToGet) {
        this.resourcesTempToGet = resourcesTempToGet;
    }
    /* Resetters */
    public void resetResourcesTempToGet()
    {
        this.resourcesTempToGet = null;
    }


    @Override
    public void activate() {
        if (resourcesTempToGet == null)
        {
            //TODO: fare qualcosa (eccezione?)
        } else
        {//Eseguo l'azione
            List<Resource> resourcesTempToGetList = this.resourcesTempToGet.getResourceList();
            for(Resource currentResource : resourcesTempToGetList)
            {
                //Aggiungo i bonus alle risorse ottenute
                //todo: da attivare sse le risorse provengono da carte sviluppo o spazi azione
                //todo: attivare anche le scelte
                Resource currentBonus = resourceBonus.getSpecificResource(currentResource.getClass());
                if( currentBonus != null )
                    currentResource.addValue(currentBonus.getValue());

                currentResource.addTempResource(super.player);
            }
            this.resetResourcesTempToGet();
        }
    }

    /* Modifiers */
    public void addResourceBonus (Resource resourceBonus)
    {
       this.resourceBonus.addSpecificResource(resourceBonus);
    }

    public void addResourceBonusChoice(ResourceList resourceBonusChoice)
    {
        this.resourceBonusChoice.add(resourceBonusChoice);
    }
}
