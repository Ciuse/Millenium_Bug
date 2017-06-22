package it.polimi.ingsw.ps31.model.card;
import it.polimi.ingsw.ps31.model.effect.ActiveEffect;
import it.polimi.ingsw.ps31.model.effect.Effect;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.StateEffect;
import it.polimi.ingsw.ps31.model.stateModel.StateLeaderCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 22/05/2017.
 */
public class LeaderCard extends Card implements ActiveEffect {
    private final int leaderId;
    private final ResourceList resourceRequest;
    private final DevelopmentCardList developmentCardRequest;
    private Effect abilityOneTimeForTurn;
    private Effect permanentAbility;
    private boolean played = false;
    private boolean usedEffect1 = false;
    private boolean usedEffect2 = false;

    public LeaderCard(String name, int leaderId, ResourceList resourceRequest, DevelopmentCardList developmentCardRequest, Effect abilityOneTimeForTurn, Effect permanentAbility) {
        super(name);
        this.leaderId = leaderId;
        this.resourceRequest = resourceRequest;
        this.developmentCardRequest = developmentCardRequest;
        this.abilityOneTimeForTurn = abilityOneTimeForTurn;
        this.permanentAbility = permanentAbility;
    }

    public List<Object> getRequirements(){          //ritorna una lista di due oggetti diversi (poi andranno castati in appositi oggetti del loro tipo)
        List<Object> requirementsList = new ArrayList<>();
        requirementsList.add(this.resourceRequest);
        requirementsList.add(this.developmentCardRequest);
        return requirementsList;
    }

    public ResourceList getResourceRequest()
    {
        return this.resourceRequest;
    }

    public DevelopmentCardList getDevelopmentCardRequest()
    {
        return this.developmentCardRequest;
    }

    public Effect getAbilityOneTimeForTurn(){
        return this.abilityOneTimeForTurn;
    }

    public Effect getPermanentAbility(){
        return this.permanentAbility;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public boolean isUsedEffect1() {
        return usedEffect1;
    }

    public void setUsedEffect1(boolean usedEffect1) {
        this.usedEffect1 = usedEffect1;
    }

    public boolean isUsedEffect2() {
        return usedEffect2;
    }

    public void setUsedEffect2(boolean usedEffect2) {
        this.usedEffect2 = usedEffect2;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public StateLeaderCard getStateLeaderCard() {
        if (this.leaderId != -1) {
            StateLeaderCard stateLeaderCard = new StateLeaderCard(playerId, leaderId, super.getName(), new StateEffect(abilityOneTimeForTurn), new StateEffect(permanentAbility), played, usedEffect1);
            return stateLeaderCard;
        } return null;
    }

    @Override
    public void activeEffectList(Player player) {
        if(this.played ==true && usedEffect1 ==false){
            if(this.abilityOneTimeForTurn!=null){
                this.abilityOneTimeForTurn.activate(player);
                setUsedEffect1(true);
            }
            if(this.abilityOneTimeForTurn!=null && usedEffect2 == false){
                this.permanentAbility.activate(player);
                setUsedEffect2(true);
            }
        }
    }

    public void resetEffectLeaderCard(){
      setUsedEffect1(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LeaderCard that = (LeaderCard) o;

        if (played != that.played) return false;
        if (usedEffect1 != that.usedEffect1) return false;
        if (usedEffect2 != that.usedEffect2) return false;
        if (resourceRequest != null ? !resourceRequest.equals(that.resourceRequest) : that.resourceRequest != null)
            return false;
        if (developmentCardRequest != null ? !developmentCardRequest.equals(that.developmentCardRequest) : that.developmentCardRequest != null)
            return false;
        if (abilityOneTimeForTurn != null ? !abilityOneTimeForTurn.equals(that.abilityOneTimeForTurn) : that.abilityOneTimeForTurn != null)
            return false;
        return permanentAbility != null ? permanentAbility.equals(that.permanentAbility) : that.permanentAbility == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (resourceRequest != null ? resourceRequest.hashCode() : 0);
        result = 31 * result + (developmentCardRequest != null ? developmentCardRequest.hashCode() : 0);
        result = 31 * result + (abilityOneTimeForTurn != null ? abilityOneTimeForTurn.hashCode() : 0);
        result = 31 * result + (permanentAbility != null ? permanentAbility.hashCode() : 0);
        result = 31 * result + (played ? 1 : 0);
        result = 31 * result + (usedEffect1 ? 1 : 0);
        result = 31 * result + (usedEffect2 ? 1 : 0);
        return result;
    }
}
