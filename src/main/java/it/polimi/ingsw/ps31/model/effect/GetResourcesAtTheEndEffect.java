package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 30/05/2017.
 *
 * Effetto tipico delle carte viola, che farà guadagnare le risorse indicate nell'effetto solo alla fine del gioco
 */
public class GetResourcesAtTheEndEffect extends GetResourceEffect {

    /**
     * @param resources lista di risorse che guadagnerò alla fine del gioco
     */
    public GetResourcesAtTheEndEffect(int cardId,ResourceList resources) {
        super(cardId,resources);
    }

    /**
     * l'effetto va ad attivarsi aggiungendo al player le risorse in un modo specifico
     * @see it.polimi.ingsw.ps31.model.actions.ActionAddFinalBonus
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        player.getPlayerActionSet().addFinalBonus(super.getResources());
    }

    @Override
    public String getNameString(){
        return "ResAtTheEnd";
    }
}
