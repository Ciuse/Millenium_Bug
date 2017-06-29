package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.stateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.stateModel.StatePersonalBoard;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getPersonal_Board_Identical_Box_Max;

/**
 * Created by giulia on 07/06/2017.
 */
public class StateViewPersonalBoard {
    private final PlayerId playerId;
    private final List<StateViewPersonalCardBox> stateViewPersonalCardBoxListGreen = new ArrayList<>();
    private final List<StateViewPersonalCardBox> stateViewPersonalCardBoxListYellow = new ArrayList<>();
    private final List<StateViewPersonalCardBox> stateViewPersonalCardBoxListBlue = new ArrayList<>();
    private final List<StateViewPersonalCardBox> stateViewPersonalCardBoxListPurple = new ArrayList<>();



    public StateViewPersonalBoard(PlayerId playerId) {
        this.playerId = playerId;
        for(int i =0;i<getPersonal_Board_Identical_Box_Max();i++){
            stateViewPersonalCardBoxListGreen.add(new StateViewPersonalCardBox(CardColor.GREEN,i));
        }
        for(int i =0;i<getPersonal_Board_Identical_Box_Max();i++){
            stateViewPersonalCardBoxListYellow.add(new StateViewPersonalCardBox(CardColor.YELLOW, i));
        }
        for(int i =0;i<getPersonal_Board_Identical_Box_Max();i++){
            stateViewPersonalCardBoxListBlue.add(new StateViewPersonalCardBox(CardColor.BLUE, i));
        }
        for(int i =0;i<getPersonal_Board_Identical_Box_Max();i++){
            stateViewPersonalCardBoxListPurple.add(new StateViewPersonalCardBox(CardColor.PURPLE,i));
        }
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public void updateState(StateCardBox  stateCardBox) {
        if (stateCardBox.getValue() != -1) { //corrisponde al valore per dire che non è valido lo stato
            if (stateCardBox.getCardColor().equals(CardColor.GREEN)) {
                for (StateViewPersonalCardBox stateViewPersonalCardBox : stateViewPersonalCardBoxListGreen
                        ) {
                    if (stateCardBox.getValue() == stateViewPersonalCardBox.getValue() && stateCardBox.getCardColor().equals(stateViewPersonalCardBox.getCardColor()))
                        stateViewPersonalCardBox.updateState(stateCardBox);

                }
            } else if (stateCardBox.getCardColor().equals(CardColor.YELLOW)) {
                for (StateViewPersonalCardBox stateViewPersonalCardBox : stateViewPersonalCardBoxListYellow
                        ) {
                    if (stateCardBox.getValue() == stateViewPersonalCardBox.getValue() && stateCardBox.getCardColor().equals(stateViewPersonalCardBox.getCardColor()))
                        stateViewPersonalCardBox.updateState(stateCardBox);

                }
            } else if (stateCardBox.getCardColor().equals(CardColor.BLUE)) {
                for (StateViewPersonalCardBox stateViewPersonalCardBox : stateViewPersonalCardBoxListBlue
                        ) {
                    if (stateCardBox.getValue() == stateViewPersonalCardBox.getValue() && stateCardBox.getCardColor().equals(stateViewPersonalCardBox.getCardColor()))
                        stateViewPersonalCardBox.updateState(stateCardBox);

                }
            } else if (stateCardBox.getCardColor().equals(CardColor.PURPLE)) {
                for (StateViewPersonalCardBox stateViewPersonalCardBox : stateViewPersonalCardBoxListPurple
                        ) {
                    if (stateCardBox.getValue() == stateViewPersonalCardBox.getValue() && stateCardBox.getCardColor().equals(stateViewPersonalCardBox.getCardColor()))
                        stateViewPersonalCardBox.updateState(stateCardBox);

                }
            }
        }
    }

    public void updateState(StatePersonalBoard statePersonalBoard) {
        if (statePersonalBoard.getPlayerId().equals(this.playerId)) {
            if (!statePersonalBoard.getStateCardBoxes().isEmpty()) {
                for (StateCardBox stateCardBox : statePersonalBoard.getStateCardBoxes()
                        ) {
                    if (stateCardBox != null) {
                        updateState(stateCardBox);
                    }
                }
            }
        }
    }

    public List<StateViewPersonalCardBox> getStateViewPersonalCardBoxListGreen() {
        return stateViewPersonalCardBoxListGreen;
    }

    public List<StateViewPersonalCardBox> getStateViewPersonalCardBoxListYellow() {
        return stateViewPersonalCardBoxListYellow;
    }

    public List<StateViewPersonalCardBox> getStateViewPersonalCardBoxListBlue() {
        return stateViewPersonalCardBoxListBlue;
    }

    public List<StateViewPersonalCardBox> getStateViewPersonalCardBoxListPurple() {
        return stateViewPersonalCardBoxListPurple;
    }
}
