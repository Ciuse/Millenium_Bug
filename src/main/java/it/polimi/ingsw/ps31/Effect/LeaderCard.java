package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Card.Card;
import it.polimi.ingsw.ps31.Card.DevelopmentCardList;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

/**
 * Created by Giuseppe on 22/05/2017.
 */
public class LeaderCard extends Card {
    private final ResourceList resourceRequest;
    private final DevelopmentCardList developmentCardRequest;
    private final Effect abilityOneTimeForTurn;
    private final Effect permanentAbility;

    public LeaderCard(String name, ResourceList resourceRequest, DevelopmentCardList developmentCardRequest, Effect abilityOneTimeForTurn, Effect permanentAbility) {
        super(name);
        this.resourceRequest = resourceRequest;
        this.developmentCardRequest = developmentCardRequest;
        this.abilityOneTimeForTurn = abilityOneTimeForTurn;
        this.permanentAbility = permanentAbility;
    }


}
