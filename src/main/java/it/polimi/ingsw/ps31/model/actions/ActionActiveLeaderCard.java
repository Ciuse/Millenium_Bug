package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 23/05/2017.
 */
public class ActionActiveLeaderCard extends Action {
    private LeaderCard leaderCard= null;
    private boolean used = false;

    public ActionActiveLeaderCard(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    @Override
    public void activate() {
        //se la carta non è attiva, controllo che il player soddisfi i requisiti di attivazione
        if (leaderCard.isPlayed() == false) {
            if(super.actionControlSet.leaderCardRequirementControl(leaderCard))
                leaderCard.setPlayed(true);
            else{
                //TODO "NON HAI ABBASTANZA RISORSE"

            }
            }
        //se la carta è attivata (perchè lo era prima o perchè è stata appena attivata) allor attivo anche l'effetto
        if (leaderCard.isPlayed() == true)
            leaderCard.activeEffectList(player);

        resetLeaderCard();
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
