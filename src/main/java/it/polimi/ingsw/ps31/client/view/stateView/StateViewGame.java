package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.stateModel.StateDevelopmentCard;
import it.polimi.ingsw.ps31.model.stateModel.StateGame;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getNumber_Of_DevelopmentCard;

/**
 * Created by giulia on 12/06/2017.
 */
public class StateViewGame {
    private int period;
    private int round;
    private PlayerId playerIdInACtion;
    private final List<StateViewDevelopmentCard> stateViewDevelopmentCardList = new ArrayList<>();

    public StateViewGame() {
        for (int i = 1;i<=getNumber_Of_DevelopmentCard();i++){
            stateViewDevelopmentCardList.add(new StateViewDevelopmentCard(i));
        }
    }


    public void updateState(StateGame stateGame){
        if(stateGame.getPeriod()!=-1){
            this.period = stateGame.getPeriod();
            this.round = stateGame.getRound();
            this.playerIdInACtion = stateGame.getPlayerIdInAction();
        }
    }

    public void updateState(StateDevelopmentCard stateDevelopmentCard){
        for (StateViewDevelopmentCard stateCard: stateViewDevelopmentCardList
             ) {
            if(stateCard.getCardId()==stateDevelopmentCard.getCardId()){
                stateCard.updateState(stateDevelopmentCard);
            }
        }
    }

    public int getPeriod() {
        return period;
    }

    public int getRound() {
        return round;
    }

    public List<StateViewDevelopmentCard> getStateViewDevelopmentCardList() {
        return stateViewDevelopmentCardList;
    }

    public PlayerId getPlayerIdInACtion() {
        return playerIdInACtion;
    }
}
