package it.polimi.ingsw.ps31.Card;
import it.polimi.ingsw.ps31.Effect.ActiveEffect;
import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.Player;

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
                &&resourceRequest.lessOrEquals(player.getResources().getPlayerResourceAsResourceList())){
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
}
