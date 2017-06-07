package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.gameThings.CouncilPrivilege;
import it.polimi.ingsw.ps31.model.gameThings.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 30/05/2017.
 */
public class ActionDiscardLeaderCard extends Action {
    private LeaderCard leaderCard = null;

    /* Constructor */
    public ActionDiscardLeaderCard(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    /* Setters & Getters */
    public void setLeaderCard(LeaderCard leaderCard) {
        this.leaderCard = leaderCard;
    }

    public LeaderCard getLeaderCard() {
        return leaderCard;
    }

    /* Resetters */
    public void resetLeaderCard()
    {
        this.leaderCard = null;
    }

    /* Activation Method*/
    @Override
    public void activate()
    {
        //Controllo che i parametri siano settati
        if (this.leaderCard == null)
        {
            //todo: eccezione
        }
        else
        {
            //segno la carta come scartata
            this.leaderCard.discardLeaderCard();

            //attivo la ricompensa (1 privilegio del consiglio)
            player.getPlayerActionSet().getResources(new ResourceList(new CouncilPrivilege(1,false)));
        }
    }
}
