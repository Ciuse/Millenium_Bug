package it.polimi.ingsw.ps31.model.bonus;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceLeaderEffectToCopy;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by giulia on 20/06/2017.
 */
public class CopyLeaderBonus extends Bonus {
    private final int leaderCardId; //carta leader Lorenzo De' Medici ,copia l'abilità di un altro leader che è stato giocato da un altro
    //giocatore e che può essere o un'abilità per turno oppure un'abilità permanente



    public CopyLeaderBonus(int leaderCardId) {
        this.leaderCardId = leaderCardId;
    }



    @Override
    public void activate(Player player) {
        for (LeaderCard leaderCard : player.getLeaderCardList()) {
            if(leaderCard.getLeaderId()==leaderCardId){
                String string = player.getPlayerId()+": Scegli l'effetto di un'altra carta leader in gioco di un altro giocatore";
                notifyViews(new MVAskChoice(player.getPlayerId(),string,new ChoiceLeaderEffectToCopy(leaderCardId)));
                LeaderCard leaderCardChosen = super.waitLeaderCardChosen(); //aspetto che il controller crei la nuova carta leader con l'id di lorenzo de medici
                player.removeLeaderCard(leaderCard);
                player.addLeaderCard(leaderCardChosen);
            }

        }

    }
}
