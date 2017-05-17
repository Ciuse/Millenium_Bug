package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

/**
 * Created by giulia on 17/05/2017.
 */
public class GetResourceFromCard extends GetResource {
    private final CardColor cardColor; // per ogni carte del colore specificato nella carta , ottengo le risorse corrispondenti
    public GetResourceFromCard(ResourceList resources, CardColor cardColor) {
        super(resources);
        this.cardColor = cardColor;
    }
}
