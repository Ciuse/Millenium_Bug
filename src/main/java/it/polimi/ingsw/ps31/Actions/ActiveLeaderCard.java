package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.Card.LeaderCard;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Giuseppe on 23/05/2017.
 */
public class ActiveLeaderCard extends Action {
    private LeaderCard leaderCard= null;

    public ActiveLeaderCard(Player player) {
        super(player);
    }

    @Override
    public void activate() {
        //leaderCard.activeLeaderCard(player); TODO FARE ALTRO?
     }
}
