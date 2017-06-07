package it.polimi.ingsw.ps31.model.card;
import it.polimi.ingsw.ps31.model.effect.ActiveEffect;
import it.polimi.ingsw.ps31.model.effect.Effect;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 22/05/2017.
 */
public class LeaderCard extends Card implements ActiveEffect {
    private final ResourceList resourceRequest;
    private final DevelopmentCardList developmentCardRequest;
    private final Effect abilityOneTimeForTurn;
    private final Effect permanentAbility;
    private Boolean activated = false;

    public LeaderCard(String name, ResourceList resourceRequest, DevelopmentCardList developmentCardRequest, Effect abilityOneTimeForTurn, Effect permanentAbility) {
        super(name);
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

    public Effect getAbilityOneTimeForTurn(){
        return this.abilityOneTimeForTurn;
    }

    public Effect getPermanentAbility(){
        return this.permanentAbility;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void activeLeaderCard(Player player){
        if(developmentCardRequest.lessOrEquals(player.getPlayerCardList())
                &&resourceRequest.lessOrEquals(player.getPlayerResources().getPlayerResourceList())){
            this.activated=true;
        }
        else {//TODO "NON HAI ABBASTANZA RISORSE"
        }
    }

    @Override
    public void activeEffectList(Player player) {
        if(this.activated==true){
            if(this.abilityOneTimeForTurn!=null){
                this.abilityOneTimeForTurn.activate(player);
            }
            if(this.abilityOneTimeForTurn!=null){
                this.permanentAbility.activate(player);

            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LeaderCard that = (LeaderCard) o;

        if (resourceRequest != null ? !resourceRequest.equals(that.resourceRequest) : that.resourceRequest != null)
            return false;
        if (developmentCardRequest != null ? !developmentCardRequest.equals(that.developmentCardRequest) : that.developmentCardRequest != null)
            return false;
        if (abilityOneTimeForTurn != null ? !abilityOneTimeForTurn.equals(that.abilityOneTimeForTurn) : that.abilityOneTimeForTurn != null)
            return false;
        if (permanentAbility != null ? !permanentAbility.equals(that.permanentAbility) : that.permanentAbility != null)
            return false;
        return activated.equals(that.activated);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (resourceRequest != null ? resourceRequest.hashCode() : 0);
        result = 31 * result + (developmentCardRequest != null ? developmentCardRequest.hashCode() : 0);
        result = 31 * result + (abilityOneTimeForTurn != null ? abilityOneTimeForTurn.hashCode() : 0);
        result = 31 * result + (permanentAbility != null ? permanentAbility.hashCode() : 0);
        result = 31 * result + activated.hashCode();
        return result;
    }
}
