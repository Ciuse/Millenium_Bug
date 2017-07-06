package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 *
 * Effetto di guadagno risorse in base al numero di carte di un certo colore
 */
public class GetResourceEffectFromCard extends GetResourceEffect {
    /**
     * colore raffigurato sull'effetto che indica la tipologia di carte che entreranno
     * in gioco nell'attivazione dell'effetto
     */
    private final CardColor cardColor;

    /**
     * @param resources lista di risorse che guadagnerò
     * @param cardColor colore della carta richiesto
     */
    public GetResourceEffectFromCard(int cardId, ResourceList resources, CardColor cardColor) {
        super(cardId,resources);
        this.cardColor = cardColor;
    }

    /**
     * Per ogni carta di un certo colore guadagnerò tot risorse
     * @see it.polimi.ingsw.ps31.model.actions.ActionGetTempResourcesFromAllEffect
     * @param player player su cui verrà attivato l'effetto
     */
    @Override
    public void activate(Player player) {
        int factor = player.getPlayerCardList().countSpecificCardColor(cardColor);
        super.getResources().multiplyResourceList(factor);
        player.getPlayerActionSet().getTempResources(super.getResources());

    }

    public CardColor getCardColor() {
        return cardColor;
    }

    @Override
    public String getNameString(){
        return "ResFromCard";
    }


}
