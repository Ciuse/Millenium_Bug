package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceListToPay;
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
    private List<ResourceList> resourceMalus = new ArrayList<>();
    private boolean doubleActivation=false;  // bonus che viene settato dall attivazione di Santa Rita
    private boolean fromCardEffect=false;

    /* Constructor */
    public ActionGetTempResourcesFromAllEffect(Player player, ActionControlSet actionControlSet)
    {
        super(player, actionControlSet);
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
            return;
        } else {//Eseguo l'azione


            List<Resource> resourcesTempToGetList = this.resourcesTempToGet.getListOfResource();
            int listToPay = 0;
            if (!resourceMalus.isEmpty()) {
                if (resourceMalus.size() > 1) {
                    player.getModel().getModelChoices().getLastModelStateForControl().setResourceListToControl(resourceMalus);
                    String string = player.getPlayerId() + ": quale risorsa non vuoi ottenere?";
                    player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceListToPay(0)));
                    listToPay = player.getModel().getModelChoices().waitIntListToPay();

                    if(listToPay==-1) {   //TIMER SCADUTO -> scelgo una lista a caso
                        listToPay = 0;
                        player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));
                    }

                    }
                for (Resource currentResource : resourcesTempToGetList) {
                    //todo: da attivare sse le risorse provengono da carte sviluppo o spazi azione
                    for (Resource resource : resourceMalus.get(listToPay).getListOfResource()
                            ) {
                        currentResource.addValue(resource.getValue());
                    }
                    currentResource.addTempResource(super.player);
                }
            } else {
                for (Resource currentResource : resourcesTempToGetList) {
                    //todo: da attivare sse le risorse provengono da carte sviluppo o spazi azione
                    currentResource.addTempResource(super.player);
                }
            }
        }
    }

    @Override
    public void activate() {

        addTempResourceToPlayer();     // se attivo l azione, una volta aggiungo sempre le risorse al player

        if (fromCardEffect && doubleActivation) {       //riattivo l azione di ottenere le risorse se ho la carta leader Santa Rita e le sto ottenendo da una Carta Sviluppo
                                                        //il fromCardEffect è true solo se l effetto è stato attivato da una carta (controllo se l id della carta associato all effetto è diverso da 0)
            addTempResourceToPlayer();
        }
        this.resetResourcesTempToGet();
        this.resetFromCardEffect();   //lo rimetto a false per evitare che si attivi anche dagli action space
    }

    /* Modifiers */
    public void addResourceMalus(ResourceList resourceBonusChoice)
    {
        this.resourceMalus.add(resourceBonusChoice);
    }
}
