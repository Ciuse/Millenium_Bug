package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 15/05/2017.
 */
public class GetResourceEffect extends Effect {
    private final ResourceList resources;

    public GetResourceEffect(int cardId,ResourceList resources){
        super(cardId);
        this.resources=resources;
    }

    public GetResourceEffect(ResourceList resources) {
        this.resources = resources;
    }

    public ResourceList getResources(){
        return this.resources;
    }

    @Override
    public void activate(Player player) {
        if(super.getCardId()!=0) {          // se l effetto ha un id associato vuol dire che proviene da una carta e quindi nel caso posso attivare il bonus di santa rita
            player.getPlayerActionSet().getGetTempResources().setFromCardEffect(true);
        }
        player.getPlayerActionSet().getTempResources(this.resources);

    }

    public String getResourceToGainString(){
        return resources.toString();
    }

    public String getNameString(){
        return "GetRes";
    }

}
