package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 20/06/2017.
 */
public class TempModelStateForLeaderChoice {
    private List<PlayerPossibleChoice> playerPossibleChoiceList= new ArrayList<>();
    private List<List<LeaderCard>> listList = new ArrayList<>();

    public TempModelStateForLeaderChoice() {
    }

    public void addPlayerPossibleChoise(PlayerId playerId, List<Integer> leaderId){
        boolean found=false;
        for (PlayerPossibleChoice choice: playerPossibleChoiceList
             ) {
            if (choice.getPlayerId() == playerId) {
                found=true;
                choice.setLeaderId(leaderId);
            }
        }
        if(!found){
            playerPossibleChoiceList.add(new PlayerPossibleChoice(playerId,leaderId));
            System.out.println("LISTA AGIGUNTA Player:"+playerId);
            for (Integer id: leaderId
                 ) {
                System.out.println("LEADER ID:"+id);

            }
        }

    }

    public List<PlayerPossibleChoice> getPlayerPossibleChoiceList() {
        return playerPossibleChoiceList;
    }

    public void setPlayerPossibleChoiceList(PlayerId playerId, List<Integer> leaderId) {
    }

    public List<List<LeaderCard>> getListList() {
        return listList;
    }

    public void setListList(List<List<LeaderCard>> listList) {
        this.listList = listList;
    }

    public void removerLeader(LeaderCard leaderCard){
        for (List list: listList
             ) {
            list.remove(leaderCard);
        }
    }
}
