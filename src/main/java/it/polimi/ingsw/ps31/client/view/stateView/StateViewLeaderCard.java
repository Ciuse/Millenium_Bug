package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.stateModel.StateEffect;
import it.polimi.ingsw.ps31.model.stateModel.StateLeaderCard;

/**
 * Created by giulia on 22/06/2017.
 */
public class StateViewLeaderCard  {
    private int leaderId;
    private String leaderName;
    private StateEffect abilityOneTimeForTurnString;
    private StateEffect permanentAbilityString;
    private boolean played;
    private boolean usedEffect1;

    public int getLeaderId() {
        return leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public StateEffect getAbilityOneTimeForTurnString() {
        return abilityOneTimeForTurnString;
    }

    public StateEffect getPermanentAbilityString() {
        return permanentAbilityString;
    }

    public boolean isPlayed() {
        return played;
    }

    public boolean isUsedEffect1() {
        return usedEffect1;
    }

    public void updateState(StateLeaderCard stateLeaderCard){
        if(stateLeaderCard.getLeaderId()!=-1) {
            this.leaderId = stateLeaderCard.getLeaderId();
            if (stateLeaderCard.getLeaderName() != null)
                this.leaderName = stateLeaderCard.getLeaderName();
            this.abilityOneTimeForTurnString = stateLeaderCard.getAbilityOneTimeForTurnString();
            this.permanentAbilityString = stateLeaderCard.getPermanentAbilityString();
            this.played = stateLeaderCard.isPlayed();
            this.usedEffect1 = stateLeaderCard.isUsedEffect1();
        }

    }
}
