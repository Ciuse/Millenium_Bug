package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by giulia on 15/05/2017.
 */
public class GetResourceEffect extends Effect {
    private final ResourceList resources;

    public GetResourceEffect(ResourceList resources){
        this.resources=resources;
    }

    public ResourceList getResources(){
        return this.resources;
    }

    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().getResources(this.resources);
    }
    public String resourceToGainString(){
        return resources.toString();
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
    public Effect getGetResource() {
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

    public String nameString(){
        return "GetRes";
    }

    @Override
    public List<String> resourcesToPayString() {
        return null;
    }

    @Override
    public List<String> resourcesToGainString() {
        return null;
    }
}
