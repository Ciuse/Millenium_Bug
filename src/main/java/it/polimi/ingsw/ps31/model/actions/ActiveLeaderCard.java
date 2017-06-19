package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 23/05/2017.
 */
public class ActiveLeaderCard extends Action {
    private LeaderCard leaderCard= null;
    private boolean used = false;

    public ActiveLeaderCard(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    @Override
    public void activate() {
        //leaderCard.activeLeaderCard(player); TODO FARE ALTRO? e settera/usare il parametro (se serve)
     }

    @Override
    public String toString() {
        return "Active Leader";
    }

    public void setLeaderCard(LeaderCard leaderCard) {
        this.leaderCard = leaderCard;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }

    public void resetLeaderCard(){
        this.leaderCard=null;
    }
}
