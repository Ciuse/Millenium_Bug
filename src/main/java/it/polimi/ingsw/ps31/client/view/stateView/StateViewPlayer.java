package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateAllFamilyMember;
import it.polimi.ingsw.ps31.model.StateModel.StateFamilyMember;
import it.polimi.ingsw.ps31.model.StateModel.StateInfoPlayer;
import it.polimi.ingsw.ps31.model.StateModel.StatePlayerResources;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.PlayerResources;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getFamily_Member_Number;

/**
 * Created by giulia on 07/06/2017.
 */
public class StateViewPlayer {
    private PlayerId playerId;
    private String nickname;
    private PlayerColor playerColor;
    private PlayerResources playerResources;
    private final List<StateViewFamilyMember> stateViewFamilyMemberList = new ArrayList<>();

    public StateViewPlayer(){
        for(int i=0; i<getFamily_Member_Number();i++){
            stateViewFamilyMemberList.add(new StateViewFamilyMember(playerId));
        }
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public String getNickname() {
        return nickname;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public PlayerResources getPlayerResources() {
        return playerResources;
    }

    public List<StateViewFamilyMember> getFamilyMembers() {
        return stateViewFamilyMemberList;
    }

    public void updateState(StateInfoPlayer stateInfoPlayer){
        if(stateInfoPlayer.getPlayerId()!=null){
            this.playerId= stateInfoPlayer.getPlayerId();
        }
        if(stateInfoPlayer.getNickname()!=null){
            this.nickname= stateInfoPlayer.getNickname();
        }
        if(stateInfoPlayer.getPlayerColor()!=null){
            this.playerColor= stateInfoPlayer.getPlayerColor();
        }
    }

    public void updateState(StatePlayerResources statePlayerResources){
        if(statePlayerResources.getPlayerResources()!=null){
            this.playerResources=statePlayerResources.getPlayerResources();
        }
    }
    public void updateState(StateAllFamilyMember stateAllFamilyMember){
        for(int i = 0;i<stateAllFamilyMember.getStateAllFamilyMember().size();i++){
            stateViewFamilyMemberList.get(i).updateStateFamily(stateAllFamilyMember.getStateAllFamilyMember().get(i));
        }
    }

    public void updateState(StateFamilyMember stateFamilyMember){
            for (StateViewFamilyMember stateViewFamilyMember: stateViewFamilyMemberList
                    ) {
                if(stateFamilyMember.getDiceColor().equals(stateViewFamilyMember.getDiceColor())) {
                stateViewFamilyMember.updateStateFamily(stateFamilyMember);
            }
        }
    }
}
