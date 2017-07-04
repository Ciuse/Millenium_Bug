package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.stateModel.StateDevelopmentCard;
import it.polimi.ingsw.ps31.model.stateModel.StateExcommunication;
import it.polimi.ingsw.ps31.model.stateModel.StateGame;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getMax_number_ofExcommunication;
import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getNumber_Of_DevelopmentCard;

/**
 * Created by giulia on 12/06/2017.
 */
public class StateViewGame {
    private int period;
    private int round;
    private int playerMaxNumber;
    private PlayerId playerIdInAction;
    private final List<StateViewDevelopmentCard> stateViewDevelopmentCardList = new ArrayList<>();
    private final List<StateViewExcommunication> stateViewExcommunicationList= new ArrayList<>();

    public StateViewGame(int playerMaxNumber) {
        this.playerMaxNumber=playerMaxNumber;
        for (int i = 1;i<=getNumber_Of_DevelopmentCard();i++){
            stateViewDevelopmentCardList.add(new StateViewDevelopmentCard(i));
        }
        for (int i = 1;i<=getMax_number_ofExcommunication();i++){
            stateViewExcommunicationList.add(new StateViewExcommunication(i));
        }
    }


    public void updateState(StateGame stateGame){
        if(stateGame.getPeriod()!=-1){

            this.period = stateGame.getPeriod();
            this.round = stateGame.getRound();
            this.playerIdInAction = stateGame.getPlayerIdInAction();
        }
    }

    public void updateState(StateDevelopmentCard stateDevelopmentCard){
        for (StateViewDevelopmentCard stateViewDevelopmentCard: stateViewDevelopmentCardList
             ) {
            if(stateViewDevelopmentCard.getCardId()==stateDevelopmentCard.getCardId()){
                stateViewDevelopmentCard.updateState(stateDevelopmentCard);
            }
        }
    }

    public void updateState(StateExcommunication stateExcommunication) {
        for (StateViewExcommunication stateViewExcommunication : stateViewExcommunicationList
                ) {
            if (stateExcommunication.getPeriod() == stateViewExcommunication.getPeriod()) {
                stateViewExcommunication.updateState(stateExcommunication);
            }
        }
    }

    public int getPlayerMaxNumber() {
        return playerMaxNumber;
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

    public List<StateViewExcommunication> getStateViewExcommunicationList() {
        return stateViewExcommunicationList;
    }

    public PlayerId getPlayerIdInAction() {
        return playerIdInAction;
    }
}
