package it.polimi.ingsw.ps31.effect;

import it.polimi.ingsw.ps31.constants.CardColor;
import it.polimi.ingsw.ps31.gameThings.ResourceList;
import it.polimi.ingsw.ps31.player.Player;

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
//        int numberCard = player.getPlayerCardList().countSpecificCardColor(cardColor);
//        GetResources getResources = new GetResources(player);
//        getResources.setResourcesToGet( super.getResources().multiplyResourceList(numberCard));
//        getResources.activate();
    }
}
