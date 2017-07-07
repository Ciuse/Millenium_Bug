package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 15/05/2017.
 *
 * Effetto generico di guadagno di risorse
 */
public class GetResourceEffect extends Effect {
    private final ResourceList resources;

    /**
     * @param resources lista di risorse che guadagnerò
     */
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

    /**
     * L'effetto farà guadagnare al player delle risorse in modo temporaneo le quali verranno aggiunte alla fine
     * del turno per evitare che le risorse vengano usate per attivare altri effetti all'interno della stessa
     * catena di azioni
     * @see it.polimi.ingsw.ps31.model.actions.ActionGetTempResourcesFromAllEffect
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        if(super.getCardId()!=0) {
            // se l effetto ha un id associato vuol dire che proviene da una carta e quindi nel caso posso attivare il bonus di santa rita
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
