package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameThings.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 17/05/2017.
 */
public class GetResourceEffectFromCard extends GetResourceEffect {
    private final CardColor cardColor; // per ogni carte del colore specificato nella carta , ottengo le risorse corrispondenti
    public GetResourceEffectFromCard(ResourceList resources, CardColor cardColor) {
        super(resources);
        this.cardColor = cardColor;
    }
    @Override
    public void activate(Player player) {
        int factor = player.getPlayerCardList().countSpecificCardColor(cardColor);
        super.getResources().multiplyResourceList(factor);
        player.getPlayerActionSet().getResources(super.getResources());

    }
}
