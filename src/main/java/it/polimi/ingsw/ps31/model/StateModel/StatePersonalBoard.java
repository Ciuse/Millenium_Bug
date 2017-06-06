package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.player.PersonalBoardCardList;

import java.util.List;

/**
 * Created by giulia on 06/06/2017.
 */
public class StatePersonalBoard extends StateInfo{
    private final List<PersonalBoardCardList> personalBoardCardList;


    public StatePersonalBoard(List<PersonalBoardCardList> personalBoardCardList) {
        this.personalBoardCardList = personalBoardCardList;
    }
}
