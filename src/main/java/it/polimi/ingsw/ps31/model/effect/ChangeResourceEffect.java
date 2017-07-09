package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceListToPay;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giulia on 17/05/2017.ù
 * Effetto che permette al player di pagare tot risorse per ottenerne altre
 */
public class ChangeResourceEffect extends Effect {
    /**
     * Lista di possibili scelte di risorse da cui pagare (una o più)
     */
    private final List<ResourceList> resourceToPayList;
    /**
     * Lista di possibili scelte di risorse da guadagnare (una o più)
     */
    private final List<ResourceList> resourceToGainList;

    public ChangeResourceEffect(int cardId, List<ResourceList> resourceToPayList, List<ResourceList> resourceToGainList) {
        super(cardId);
        this.resourceToPayList = resourceToPayList;
        this.resourceToGainList = resourceToGainList;
    }



    public List<ResourceList> getResourceToPayList() {
        return resourceToPayList;
    }

    public List<ResourceList> getResourceToGainList() {
        return resourceToGainList;
    }


    /**
     * se l'effetto ha una sola possibile lista da pagare e guadagnare allora non verrà chiesto all'utente nulla
     * mentre se vi sono più liste in base alla risposta del giocatore verra attivata la lista i-esima da pagare
     * e la lista i-esima da guadagnare visto che ogni lista da pagare ha sempre associata una e una sola lista
     * da guadgnare e viceversa
     * @see it.polimi.ingsw.ps31.model.actions.ActionGetTempResourcesFromAllEffect
     * @see it.polimi.ingsw.ps31.model.actions.ActionPayResources
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        if(resourceToGainList.size()==1&&resourceToPayList.size()==1){
            player.getPlayerActionSet().payResources(resourceToPayList.get(0));
            player.getPlayerActionSet().getTempResources(resourceToGainList.get(0));
        }else{
            String string=player.getPlayerId()+"Quale delle due risorse vuoi pagare/guadagnare?";
            player.getModel().getModelChoices().getLastModelStateForControl().setResourceListToControl(resourceToPayList);
            player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(),string, new ChoiceListToPay(super.cardId)));
            int listChoose = player.getModel().getModelChoices().waitIntListToPay();

            if(player.getActionControlSet().payResourceControl(resourceToPayList.get(listChoose))){
                player.getPlayerActionSet().payResources(resourceToPayList.get(listChoose));
                player.getPlayerActionSet().getTempResources(resourceToGainList.get(listChoose));
            }
            else {
                player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, "Non hai abbastanza risorse per attivare quello che hai detto"));
            }
        }
    }


    public String getNameString(){
        return "Change";
    }

    public List<String> getResourceToPayListString(){
        List<String> stringResourceToPayList = new ArrayList<>();
        for (ResourceList resourceList:resourceToPayList
                ) {
            stringResourceToPayList.add(resourceList.toString());

        } return stringResourceToPayList;
    }

    public List<String> getResourceToGainListString(){
        List<String> stringResourceToGainList = new ArrayList<>();
        for (ResourceList resourceList:resourceToGainList
                ) {
            stringResourceToGainList.add(resourceList.toString());
        } return stringResourceToGainList;
    }
}
