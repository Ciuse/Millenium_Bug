package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceLeaderToActive;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 23/05/2017.
 */
public class ActionActiveLeaderCard extends Action {
    private LeaderCard leaderCard= null;

    public ActionActiveLeaderCard(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    @Override
    public void activate() {
        player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(),"Quale leader vuoi attivare?",new ChoiceLeaderToActive()));
        this.leaderCard=player.getModel().getModelChoices().waitLeaderCardChosen();
        //se la carta non è attiva, controllo che il player soddisfi i requisiti di attivazione
        if (!leaderCard.isPlayed()) {
            if (super.actionControlSet.leaderCardRequirementControl(leaderCard))
                leaderCard.setPlayed(true);
            else {
                player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getLeaderCardRequirementControl().getControlStringError()));
            }
        }
        //se la carta è attivata (perchè lo era prima o perchè è stata appena attivata) allora attivo anche l'effetto
        if (leaderCard.isPlayed())
            leaderCard.activeEffectList(player);

        player.getModel().notifyViews(new MVUpdateState("Aggiornato stato leader card",leaderCard.getStateLeaderCard()));

        resetLeaderCard();
    }

    @Override
    public String toString() {
        return "Active Leader";
    }

    public void setLeaderCard(LeaderCard leaderCard) {
        this.leaderCard = leaderCard;
    }

    public void resetLeaderCard(){
        this.leaderCard=null;
    }
}
