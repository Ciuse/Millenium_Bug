package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giulia on 17/05/2017.
 */
public class ChangeResourceEffect extends Effect {
    private final List<ResourceList> resourceToPayList; // alcune carte hanno una doppia scelta sulle risorse da pagare per ottenere nuove risorse
    private final List<ResourceList> resourceToGainList; // alcune carte potrebbero avere una doppia scelta di risorse da guadagnare

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

    //TODO spostare la richiesta di inserimento alla view
    @Override
    public void activate(Player player) {
        if(resourceToGainList.size()==1&&resourceToPayList.size()==1){
            player.getPlayerActionSet().payResources(resourceToPayList.get(0));
            player.getPlayerActionSet().getTempResources(resourceToGainList.get(0));
        }else{
          //TODO  super.notifyViews(new MVAskChoice(1,1,resourceToPayList.size()));
            int listChoose = super.waitIntChosen();
            player.getPlayerActionSet().payResources(resourceToPayList.get(listChoose));
            player.getPlayerActionSet().getTempResources(resourceToGainList.get(listChoose));
        }
    }


    public String nameString(){
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
