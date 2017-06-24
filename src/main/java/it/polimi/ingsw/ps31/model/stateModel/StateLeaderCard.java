package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.PlayerId;

/**
 * Created by giulia on 22/06/2017.
 */
public class StateLeaderCard extends StateType{
    private final PlayerId playerId;
    private int leaderId=-1;
    private String leaderName=null;
    private final StateEffect abilityOneTimeForTurnString;
    private final StateEffect permanentAbilityString;
    private final boolean played;
    private final boolean usedEffect1;

    public StateLeaderCard(PlayerId playerId, int leaderId, String leaderName, StateEffect abilityOneTimeForTurnString, StateEffect permanentAbilityString, boolean played, boolean usedEffect1) {
        this.playerId = playerId;
        this.leaderId = leaderId;
        this.leaderName = leaderName;
        this.abilityOneTimeForTurnString = abilityOneTimeForTurnString;
        this.permanentAbilityString = permanentAbilityString;
        this.played = played;
        this.usedEffect1 = usedEffect1;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

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

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}