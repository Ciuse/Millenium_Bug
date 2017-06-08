package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getPersonal_Board_Identical_Box_Max;

/**
 * Created by giulia on 07/06/2017.
 */
public class StateViewPersonalBoard {
    private PlayerId playerId;
    private final List<StateViewCardBox> stateViewCardBoxListGreen = new ArrayList<>();
    private final List<StateViewCardBox> stateViewCardBoxListYellow = new ArrayList<>();
    private final List<StateViewCardBox> stateViewCardBoxListBlue = new ArrayList<>();
    private final List<StateViewCardBox> stateViewCardBoxListPurple = new ArrayList<>();



    public StateViewPersonalBoard() {
        for(int i =0;i<getPersonal_Board_Identical_Box_Max();i++){
            stateViewCardBoxListGreen.add(new StateViewCardBox(CardColor.GREEN));
        }
        for(int i =0;i<getPersonal_Board_Identical_Box_Max();i++){
            stateViewCardBoxListYellow.add(new StateViewCardBox(CardColor.YELLOW));
        }
        for(int i =0;i<getPersonal_Board_Identical_Box_Max();i++){
            stateViewCardBoxListBlue.add(new StateViewCardBox(CardColor.BLUE));
        }
        for(int i =0;i<getPersonal_Board_Identical_Box_Max();i++){
            stateViewCardBoxListPurple.add(new StateViewCardBox(CardColor.PURPLE));
        }
    }

    public PlayerId getPlayerId() {
        return playerId;
    }
}
