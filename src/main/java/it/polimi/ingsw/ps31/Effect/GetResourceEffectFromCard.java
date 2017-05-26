package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

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
