package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 18/05/2017.
 *
 * Effetto di guadagno risorse in base alla quantità in possesso di un altra specifica risorsa
 */
public class GetResourceFromResourceEffect extends GetResourceEffect {
    /**
     * Risorsa indicata sull'effetto in base alla quale guadagnerò le risorse
     */
    private final Resource requiredResource;

    /**
     *
     * @param requiredResource risorsa richiesta
     * @param resourceGained lista di risosrse che guadagnerò
     */
    public GetResourceFromResourceEffect(int cardId,Resource requiredResource,ResourceList resourceGained) {
        super(cardId,resourceGained);
        this.requiredResource = requiredResource;
    }

    /**
     * Per ogni risorsa di un certo tipo guadagnerò tot risorse diviso il valore della risorsa richiesta
     * @see it.polimi.ingsw.ps31.model.actions.ActionGetTempResourcesFromAllEffect
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        int factor = player.getPlayerResources().getSpecificResource(requiredResource.getClass()).getValue()%requiredResource.getValue();
        super.getResources().multiplyResourceList(factor);
        player.getPlayerActionSet().getTempResources(super.getResources());
    }

    public String getRequiredResourceString(){
        return requiredResource.toString();
    }

    public String getNameString(){
        return "ResFromRes";
    }
}
