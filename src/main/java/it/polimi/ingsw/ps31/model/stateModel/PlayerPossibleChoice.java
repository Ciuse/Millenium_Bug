package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.List;

/**
 * Created by Giuseppe on 20/06/2017.
 * Stato di supporto al draft dei leader, contiene le informazioni del player associate alle carte leader che può scegliere
 * Serve al controller per assicurarsi che il giocatore ottenga uno dei leader assegnati al suo draft senza aver modo di imbrogliare
 *
 * @see StateType
 * @see it.polimi.ingsw.ps31.model.game.GameLogic
 * @see it.polimi.ingsw.ps31.controller.Controller
 *
 */
public class PlayerPossibleChoice {
    private PlayerId playerId;
    private List<Integer> leaderId;

    public PlayerPossibleChoice(PlayerId playerId, List<Integer> leaderId) {
        this.playerId = playerId;
        this.leaderId = leaderId;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public List<Integer> getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(List<Integer> leaderId) {
        this.leaderId = leaderId;
    }
}
