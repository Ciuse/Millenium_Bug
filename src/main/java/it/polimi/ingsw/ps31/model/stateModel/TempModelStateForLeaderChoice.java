package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 20/06/2017.
 */
public class TempModelStateForLeaderChoice {
    private List<PlayerPossibleChoice> playerPossibleChoiceList= new ArrayList<>();

    public void addPlayerPossibleChoide(PlayerId playerId, List<Integer> leaderId){
        playerPossibleChoiceList.add(new PlayerPossibleChoice(playerId,leaderId));
    }

    public List<PlayerPossibleChoice> getPlayerPossibleChoiceList() {
        return playerPossibleChoiceList;
    }
}
