package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceLeaderToDiscard;
import it.polimi.ingsw.ps31.model.gameResource.CouncilPrivilege;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
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
    public void activate() {
        player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), "Quale leader non giocato vuoi scartare?", new ChoiceLeaderToDiscard()));
        this.leaderCard = player.getModel().getModelChoices().waitLeaderCardChosen();
        //Controllo che i parametri siano settati

        if (!leaderCard.isPlayed()) {
            player.removeLeaderCard(leaderCard);
            //attivo la ricompensa (1 privilegio del consiglio)
            player.getPlayerActionSet().getResources(new ResourceList(new CouncilPrivilege(1, false)));
        }

        resetLeaderCard();
        player.getModel().notifyViews(new MVUpdateState("Aggiornato stato leader card", leaderCard.getStateLeaderCard()));
    }

    @Override
    public String toString() {
        return "DiscardLeader";
    }
}
