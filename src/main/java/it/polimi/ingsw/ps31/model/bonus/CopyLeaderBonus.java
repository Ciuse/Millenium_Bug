package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceLeaderEffectToCopy;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 20/06/2017.
 *
 * Bonus speciale unico della carta leader "Lorenzo De Medici" il quale ti permette
 * di copiare l'abilità di un altro leader già giocato da un altro player
 * @see it.polimi.ingsw.ps31.controller.Controller
 */
public class CopyLeaderBonus extends Bonus {
    private final int leaderCardId;



    public CopyLeaderBonus(int leaderCardId) {
        this.leaderCardId = leaderCardId;
    }


    /**
     * Sostituisco la mia carta Leader con quella copiata che il controller mi ritornerà (avrà lo stesso nome e id della carta originale che possiede questo bonus)
     */
    @Override
    public void activate(Player player) {
        LeaderCard leaderCardToAdd=null;
        LeaderCard leaderCardToRemove=null;
        for (LeaderCard leaderCard : player.getLeaderCardList()) {
            if(leaderCard.getLeaderId()==leaderCardId){
                String string = player.getPlayerId()+": Scegli l'effetto di un'altra carta leader in gioco di un altro giocatore";
                player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(),string,new ChoiceLeaderEffectToCopy(leaderCardId)));
                leaderCardToAdd = player.getModel().getModelChoices().waitLeaderCardChosen(); //aspetto che il controller crei la nuova carta leader con l'id di lorenzo de medici
                leaderCardToRemove=leaderCard;
                break;
            }
        }
        player.removeLeaderCard(leaderCardToRemove);
        player.addLeaderCard(leaderCardToAdd);
    }
}
