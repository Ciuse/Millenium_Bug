package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.stateModel.*;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.*;

/**
 * Created by giulia on 07/06/2017.
 */
public class StateViewPlayer {
    private final PlayerId playerId;
    private String nickname;
    private PlayerColor playerColor;
    private ResourceList playerResources;
    private final List<StateViewFamilyMember> stateViewFamilyMemberList = new ArrayList<>();
    private final List<StateViewMarkerDisc> stateViewMarkerDiscList = new ArrayList<>();
    private StateViewPersonalBonusTiles stateViewPersonalBonusTiles;
    private List<String> stringPlayerAction;
    private List<StateViewLeaderCard> stateViewLeaderCardList = new ArrayList<>();


    public StateViewPlayer(PlayerId playerId){
        this.playerId = playerId;
        for(int i=0; i<getFamily_Member_Number();i++){
            stateViewFamilyMemberList.add(new StateViewFamilyMember(this.playerId, this.playerColor));
        }
        for(int i=0;i<getMax_number_Of_MarkerDisc();i++){
            stateViewMarkerDiscList.add(new StateViewMarkerDisc(this.playerId,getStringPointResourceType()[i]));
        }

    }

    public StateViewPersonalBonusTiles getStateViewPersonalBonusTiles() {
        return stateViewPersonalBonusTiles;
    }

    public List<StateViewLeaderCard> getStateViewLeaderCardList() {
        return stateViewLeaderCardList;
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

    public ResourceList getPlayerResources() {
        return playerResources;
    }

    public List<StateViewFamilyMember> getStateViewFamilyMemberList() {
        return stateViewFamilyMemberList;
    }

    public List<String>getStringPlayerAction() {
        return stringPlayerAction;
    }

    public List<StateViewMarkerDisc> getStateViewMarkerDiscList() {
        return stateViewMarkerDiscList;
    }


    public void updateState(StatePlayer stateInfoPlayer){
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
    public void updateState(StateLeaderCard stateLeaderCard) {
        if (stateLeaderCard.getLeaderId() != -1) {
            boolean found = false;
            if (stateViewLeaderCardList.size() > 0) {
                for (StateViewLeaderCard stateViewLeaderCard : stateViewLeaderCardList
                        ) {
                    if (stateLeaderCard.getLeaderId() == stateViewLeaderCard.getLeaderId()) {
                        stateViewLeaderCard.updateState(stateLeaderCard);
                        found = true;
                    }

                }

            }
            if (found == false) {
                StateViewLeaderCard stateViewLeaderCardNew = new StateViewLeaderCard();
                stateViewLeaderCardNew.updateState(stateLeaderCard);
                stateViewLeaderCardList.add(stateViewLeaderCardNew);
            }
        }
    }

    public void updateState(StatePersonalBonusTiles statePersonalBonusTiles){
        if(statePersonalBonusTiles.getPersonalBonusTilesId()==-1){
            if(this.stateViewPersonalBonusTiles==null){
                List<StateViewEffect> personalTilesEffectList = new ArrayList<>();
                int i=0;
                for (StateEffect effect:statePersonalBonusTiles.getStateEffectList()
                     ) {
                    personalTilesEffectList.add(new StateViewEffect());
                    personalTilesEffectList.get(i).updateState(effect);
                    i++;
                }
                this.stateViewPersonalBonusTiles = new StateViewPersonalBonusTiles(playerId, statePersonalBonusTiles.getPersonalBonusTilesId(),personalTilesEffectList);
            }
        }
    }
}
