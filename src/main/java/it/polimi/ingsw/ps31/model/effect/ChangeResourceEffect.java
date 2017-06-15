package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.model.constants.CardColor;
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
            super.notifyViews(new MVAskChoice(1,1,resourceToPayList.size()));
            int listChoose = super.waitIntChosen();
            player.getPlayerActionSet().payResources(resourceToPayList.get(listChoose));
            player.getPlayerActionSet().getTempResources(resourceToGainList.get(listChoose));
        }
    }


    public String nameString(){
        return "Change";
    }

    public List<String> resourcesToPayString(){
        List<String> stringResourceToPayList = new ArrayList<>();
        for (ResourceList resourceList:resourceToGainList
                ) {
            stringResourceToPayList.add(resourceList.toString());

        } return stringResourceToPayList;
    }

    public List<String> resourcesToGainString(){
        List<String> stringResourceToGainList = new ArrayList<>();
        for (ResourceList resourceList:resourceToGainList
                ) {
            stringResourceToGainList.add(resourceList.toString());
        } return stringResourceToGainList;
    }

    @Override
    public String resourceToGainString() {
        return null;
    }

    @Override
    public String requiredResourceString() {
        return null;
    }

    @Override
    public int getBasicValue() {
        return 0;
    }

    @Override
    public int getDiceValue() {
        return 0;
    }

    @Override
    public CardColor getCardColor() {
        return null;
    }


    @Override
    public GetResourceEffect getGetResourceEffect() {
        return null;
    }

    @Override
    public String resourceDiscountString() {
        return null;
    }

    @Override
    public GetResourceEffectFromCard getGetResourceEffectFromCard() {
        return null;
    }

    @Override
    public ChangeResourceEffect getChangeResourceEffect() {
        return null;
    }
}
