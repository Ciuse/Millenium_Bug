package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.*;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.player.PlayerResources;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getFamily_Member_Number;
import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getMax_number_Of_MarkerDisc;
import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getStringPointResourceType;

/**
 * Created by giulia on 07/06/2017.
 */
public class StateViewPlayer {
    private final PlayerId playerId;
    private String nickname;
    private PlayerColor playerColor;
    private PlayerResources playerResources;
    private final List<StateViewFamilyMember> stateViewFamilyMemberList = new ArrayList<>();
    private final List<StateViewMarkerDisc> stateViewMarkerDiscList = new ArrayList<>();
    private List<String> stringPlayerAction;


    public StateViewPlayer(PlayerId playerId){
        this.playerId = playerId;
        for(int i=0; i<getFamily_Member_Number();i++){
            stateViewFamilyMemberList.add(new StateViewFamilyMember(this.playerId));
        }
        for(int i=0;i<getMax_number_Of_MarkerDisc();i++){
            stateViewMarkerDiscList.add(new StateViewMarkerDisc(this.playerId,getStringPointResourceType()[i]));
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

    public List<StateViewFamilyMember> getStateViewFamilyMemberList() {
        return stateViewFamilyMemberList;
    }

    public List<String> getStringPlayerAction() {
        return stringPlayerAction;
    }

    public List<StateViewMarkerDisc> getStateViewMarkerDiscList() {
        return stateViewMarkerDiscList;
    }


    public void updateState(StateInfoPlayer stateInfoPlayer){
        if(stateInfoPlayer.getPlayerId().equals(this.playerId)) {
            if (stateInfoPlayer.getNickname() != null) {
                this.nickname = stateInfoPlayer.getNickname();
            }
            if (stateInfoPlayer.getPlayerColor() != null) {
                this.playerColor = stateInfoPlayer.getPlayerColor();
            }
        }
    }

    public void updateState(StatePlayerResources statePlayerResources){
        if(statePlayerResources.getPlayerResources()!=null){
            this.playerResources=statePlayerResources.getPlayerResources();
        }
    }
    public void updateState(StateAllFamilyMember stateAllFamilyMember){
        for(int i = 0;i<stateAllFamilyMember.getStateAllFamilyMember().size();i++){
            stateViewFamilyMemberList.get(i).updateState(stateAllFamilyMember.getStateAllFamilyMember().get(i));
        }
    }

    public void updateState(StateFamilyMember stateFamilyMember){
            for (StateViewFamilyMember stateViewFamilyMember: stateViewFamilyMemberList
                    ) {
                if(stateFamilyMember.getDiceColor().equals(stateViewFamilyMember.getDiceColor())) {
                stateViewFamilyMember.updateState(stateFamilyMember);
            }
        }
    }

    public void updateState(StatePlayerAction statePlayerAction){
        this.stringPlayerAction = statePlayerAction.getStringPlayerAction();
    }

    public void updateState(StateMarkerDisc stateMarkerDisc){
        for (StateViewMarkerDisc stateViewMarkerDisc : stateViewMarkerDiscList
                ) {
            if(stateMarkerDisc.getStringResourceType().equals(stateViewMarkerDisc.getStringResourceType())&&
                    stateMarkerDisc.getPlayerId().equals(stateViewMarkerDisc.getPlayerId())){
                stateViewMarkerDisc.updateState(stateMarkerDisc);
            }
        }
    }
}
