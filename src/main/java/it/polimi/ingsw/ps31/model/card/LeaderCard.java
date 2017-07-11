package it.polimi.ingsw.ps31.model.card;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.model.effect.ActiveEffect;
import it.polimi.ingsw.ps31.model.effect.Effect;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.StateEffect;
import it.polimi.ingsw.ps31.model.stateModel.StateLeaderCard;

/**
 * Created by Giuseppe on 22/05/2017.
 *
 * Cara Leader
 *
 * I leader sono dotati di una lista di costi risorse, una lista di costi carte, due effetti, e 3 flag per sapere
 * se sono stati giocati, e se i loro effetti sono stati attivati o no.
 *
 * @see it.polimi.ingsw.ps31.model.actionControls.LeaderCardRequirementControl
 * @see it.polimi.ingsw.ps31.model.effect.EffectList
 * @see ResourceList
 * @see ActiveEffect
 */
public class LeaderCard extends Card implements ActiveEffect {
    private PlayerId playerId;
    private final int leaderId;
    private final ResourceList resourceRequest;
    private final DevelopmentCardList developmentCardRequest;
    private final int numberOfCardOfTheSameType;
    private Effect abilityOneTimeForTurn;
    private Effect permanentAbility;
    private Boolean played = false;
    private boolean usedEffect1 = false;
    private boolean usedEffect2 = false;

    public LeaderCard(String name, int leaderId, ResourceList resourceRequest, DevelopmentCardList developmentCardRequest, int numberOfCardOfTheSameType, Effect abilityOneTimeForTurn, Effect permanentAbility) {
        super(name);
        this.leaderId = leaderId;
        this.resourceRequest = resourceRequest;
        this.developmentCardRequest = developmentCardRequest;
        this.numberOfCardOfTheSameType = numberOfCardOfTheSameType;
        this.abilityOneTimeForTurn = abilityOneTimeForTurn;
        this.permanentAbility = permanentAbility;
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

    public Boolean isPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
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

    public int getNumberOfCardOfTheSameType() {
        return numberOfCardOfTheSameType;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public void setPlayerId(PlayerId playerId) {
        this.playerId = playerId;
    }

    public StateLeaderCard getStateLeaderCard() {
        if (this.leaderId != -1) {
           return new StateLeaderCard(playerId, leaderId, super.getName(), new StateEffect(abilityOneTimeForTurn), new StateEffect(permanentAbility), played, usedEffect1);
        } return null;
    }

    @Override
    public void activeEffectList(Player player) {
        if(this.played && !usedEffect1){
            if(this.abilityOneTimeForTurn!=null){
                this.abilityOneTimeForTurn.activate(player);
                setUsedEffect1(true);
            }
            if(this.permanentAbility!=null && !usedEffect2){
                this.permanentAbility.activate(player);
                setUsedEffect2(true);
            }
        }
    }

    public void resetEffectLeaderCard(Player player){
      setUsedEffect1(false);
        player.getModel().notifyViews(new MVUpdateState("Aggiornato stato leader card", this.getStateLeaderCard()));

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
