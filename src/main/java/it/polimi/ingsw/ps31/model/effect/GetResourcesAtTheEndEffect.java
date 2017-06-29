package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 30/05/2017.
 */
public class GetResourcesAtTheEndEffect extends GetResourceEffect {

    public GetResourcesAtTheEndEffect(int cardId,ResourceList resources) {
        super(cardId,resources);
    }
    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().addFinalBonus(super.getResources());
    }

    @Override
    public String getNameString(){
        return "ResAtTheEnd";
    }
}
