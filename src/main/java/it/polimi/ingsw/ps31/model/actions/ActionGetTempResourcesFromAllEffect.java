package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giulia on 15/06/2017.
 */
public class ActionGetTempResourcesFromAllEffect extends Action {
    private ResourceList resourcesTempToGet = null;
    private ResourceList resourceBonus = new ResourceList();
    private List<ResourceList> resourceBonusChoice = new ArrayList<>();
    private boolean doubleActivation=false;  // bonus che viene settato dall attivazione di Santa Rita
    private boolean fromCardEffect=false;

    /* Constructor */
    public ActionGetTempResourcesFromAllEffect(Player player, ActionControlSet actionControlSet)
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

    public void resetFromCardEffect()
    {
        this.fromCardEffect = false;
    }

    public void setFromCardEffect(boolean fromCardEffect) {
        this.fromCardEffect = fromCardEffect;
    }

    public void setDoubleActivation(boolean doubleActivation) {
        this.doubleActivation = doubleActivation;
    }

    public void addTempResourceToPlayer() {
        if (resourcesTempToGet == null) {
            //TODO: fare qualcosa (eccezione?)
        } else {//Eseguo l'azione
            List<Resource> resourcesTempToGetList = this.resourcesTempToGet.getResourceList();
            for (Resource currentResource : resourcesTempToGetList) {
                //Aggiungo i bonus alle risorse ottenute
                //todo: da attivare sse le risorse provengono da carte sviluppo o spazi azione
                //todo: attivare anche le scelte
                Resource currentBonus = resourceBonus.getSpecificResource(currentResource.getClass());
                if (currentBonus != null)
                    currentResource.addValue(currentBonus.getValue());

                currentResource.addTempResource(super.player);
            }
        }
    }

    @Override
    public void activate() {

        addTempResourceToPlayer();     // se attivo l azione una volta aggiungo sempre le risorse al player

        if (fromCardEffect && doubleActivation) {       //riattivo l azione di ottenere le risorse se ho la carta leader Santa Rita e le sto ottenendo da una Carta Sviluppo
            addTempResourceToPlayer();
        }
        this.resetResourcesTempToGet();
        this.resetFromCardEffect();
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
