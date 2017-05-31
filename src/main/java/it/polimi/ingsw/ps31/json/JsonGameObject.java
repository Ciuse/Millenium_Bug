package it.polimi.ingsw.ps31.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.ps31.bonus.*;
import it.polimi.ingsw.ps31.card.*;
import it.polimi.ingsw.ps31.card.Character;
import it.polimi.ingsw.ps31.effect.*;
import it.polimi.ingsw.ps31.gameThings.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 11/05/2017.
 */
public class JsonGameObject {
    private DevelopmentCardList developementCardList;
    private List<LeaderCard> leaderCardList;
    private List<List<EffectList>> towerActionSpaceEffectList;
    private List<EffectList> actionSpaceEffectList;
    private VictoryPoint[] faithTrackExtraValue;
    private VictoryPoint[] bonusVictoryPointFromCharacterCard;
    private VictoryPoint[] bonusVictoryPointFromTerritory;
    private VictoryPoint[] bonusVictoryPointFromMilitaryTrack;
    private MilitaryStrength[] requiredMilitaryStrengthForTerritory;
    private VictoryPoint bonusVictoryPointFromPlayerResources;

    public JsonGameObject() {
    }

    public static Gson gsonGameBuilder(){       //TODO assicurarsi che ci siano tutti i sottotipi di queste classi astratte

        //Serve per poter far riconoscere a json una Lista di classi polimorfe
        RuntimeTypeAdapterFactory<Resource> resourceAdapterFactory = RuntimeTypeAdapterFactory.of(Resource.class, "ResourceType");
        resourceAdapterFactory.registerSubtype(Coin.class, "Coin");
        resourceAdapterFactory.registerSubtype(Wood.class, "Wood");
        resourceAdapterFactory.registerSubtype(Stone.class, "Stone");
        resourceAdapterFactory.registerSubtype(Servant.class, "Servant");
        resourceAdapterFactory.registerSubtype(FaithPoint.class, "FaithPoint");
        resourceAdapterFactory.registerSubtype(MilitaryStrength.class, "MilitaryStrength");
        resourceAdapterFactory.registerSubtype(VictoryPoint.class, "VictoryPoint");
        resourceAdapterFactory.registerSubtype(CouncilPrivilege.class, "CouncilPrivilege");

        RuntimeTypeAdapterFactory<DevelopmentCard> developementCardAdapterFactory = RuntimeTypeAdapterFactory.of(DevelopmentCard.class, "CardType");
        developementCardAdapterFactory.registerSubtype(Territory.class, "Territory");
        developementCardAdapterFactory.registerSubtype(Building.class, "Building");
        developementCardAdapterFactory.registerSubtype(Character.class, "Character");
        developementCardAdapterFactory.registerSubtype(Venture.class, "Venture");

        RuntimeTypeAdapterFactory<Effect> effectAdapterFactory = RuntimeTypeAdapterFactory.of(Effect.class, "EffectType");
        effectAdapterFactory.registerSubtype(BonusAndMalusEffect.class, "BonusAndMalusEffect");
        effectAdapterFactory.registerSubtype(ChangeResourceEffect.class, "ChangeResourceEffect");
        effectAdapterFactory.registerSubtype(ChooseCardEffect.class, "ChooseCardEffect");
        effectAdapterFactory.registerSubtype(ChooseCardEffectWithDiscount.class, "ChooseCardEffectWithDiscount");
        effectAdapterFactory.registerSubtype(GenericHarvestActivation.class, "GenericHarvestActivation");
        effectAdapterFactory.registerSubtype(GenericProductionActivation.class, "GenericProductionActivation");
        effectAdapterFactory.registerSubtype(GetResourceEffect.class, "GetResourceEffect");
        effectAdapterFactory.registerSubtype(GetResourceEffectFromCard.class, "GetResourceEffectFromCard");
        effectAdapterFactory.registerSubtype(GetResourceFromResourceEffect.class, "GetResourceFromResourceEffect");
        effectAdapterFactory.registerSubtype(GetResourcesAtTheEndEffect.class,"GetResourcesAtTheEndEffect");
        effectAdapterFactory.registerSubtype(HarvestActivationFromCard.class, "HarvestActivationFromCard");
        effectAdapterFactory.registerSubtype(HarvestEffect.class, "HarvestEffect");
        effectAdapterFactory.registerSubtype(ProductionActivationFromCard.class, "ProductionActivationFromCard");
        effectAdapterFactory.registerSubtype(ProductionEffect.class, "ProductionEffect");

        RuntimeTypeAdapterFactory<Bonus> bonusAdapterFactory = RuntimeTypeAdapterFactory.of(Bonus.class, "BonusType");
        bonusAdapterFactory.registerSubtype(CardDiscountBonus.class, "CardDiscountBonus");
        bonusAdapterFactory.registerSubtype(HarvestBonus.class, "HarvestBonus");
        bonusAdapterFactory.registerSubtype(NoImmediateEffectBonus.class, "NoImmediateEffectBonus");
        bonusAdapterFactory.registerSubtype(ProductionBonus.class, "ProductionBonus");


        //TODO MANCANO I LEADER EFFECT



//Creazione del builder adatto a riconoscere tutti gli oggetti polimorfi
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .registerTypeAdapterFactory(resourceAdapterFactory)
                .registerTypeAdapterFactory(developementCardAdapterFactory)
                .registerTypeAdapterFactory(effectAdapterFactory)
                .registerTypeAdapterFactory(bonusAdapterFactory);
        Gson gson = builder.create();

        return gson;
    }


/*Getters & Setters*/

    public DevelopmentCardList getDevelopementCardList() {
        return developementCardList;
    }

    public void setDevelopementCardList(DevelopmentCardList developementCardList) {
        this.developementCardList = developementCardList;
    }

    public List<LeaderCard> getLeaderCardList() {
        return new ArrayList<>(leaderCardList);
    }

    public void setLeaderCardList(List<LeaderCard> leaderCardList) {
        this.leaderCardList = leaderCardList;
    }

    public List<List<EffectList>> getTowerActionSpaceEffectList() {
        return towerActionSpaceEffectList;
    }

    public void setTowerActionSpaceEffectList(List<List<EffectList>> towerActionSpaceEffectList) {
        this.towerActionSpaceEffectList = towerActionSpaceEffectList;
    }

    public List<EffectList> getActionSpaceEffectList() {
        return new ArrayList<>(actionSpaceEffectList);
    }

    public void setActionSpaceEffectList(List<EffectList> actionSpaceEffectList) {
        this.actionSpaceEffectList = actionSpaceEffectList;
    }

    public VictoryPoint[] getFaithTrackExtraValue() {
        return faithTrackExtraValue.clone();
    }

    public void setFaithTrackExtraValue(VictoryPoint[] faithTrackExtraValue) {
        this.faithTrackExtraValue = faithTrackExtraValue;
    }

    public VictoryPoint[] getBonusVictoryPointFromCharacterCard() {
        return bonusVictoryPointFromCharacterCard.clone();
    }

    public void setBonusVictoryPointFromCharacterCard(VictoryPoint[] bonusVictoryPointFromCharacterCard) {
        this.bonusVictoryPointFromCharacterCard = bonusVictoryPointFromCharacterCard;
    }

    public VictoryPoint[] getBonusVictoryPointFromTerritory() {
        return bonusVictoryPointFromTerritory.clone();
    }

    public void setBonusVictoryPointFromTerritory(VictoryPoint[] bonusVictoryPointFromTerritory) {
        this.bonusVictoryPointFromTerritory = bonusVictoryPointFromTerritory;
    }

    public VictoryPoint[] getBonusVictoryPointFromMilitaryTrack() {
        return bonusVictoryPointFromMilitaryTrack.clone();
    }

    public void setBonusVictoryPointFromMilitaryTrack(VictoryPoint[] bonusVictoryPointFromMilitaryTrack) {
        this.bonusVictoryPointFromMilitaryTrack = bonusVictoryPointFromMilitaryTrack;
    }

    public MilitaryStrength[] getRequiredMilitaryStrengthForTerritory() {
        return requiredMilitaryStrengthForTerritory.clone();
    }

    public void setRequiredMilitaryStrengthForTerritory(MilitaryStrength[] requiredMilitaryStrengthForTerritory) {
        this.requiredMilitaryStrengthForTerritory = requiredMilitaryStrengthForTerritory;
    }

    public VictoryPoint getBonusVictoryPointFromPlayerResources() {
        return bonusVictoryPointFromPlayerResources;
    }

    public void setBonusVictoryPointFromPlayerResources(VictoryPoint bonusVictoryPointFromPlayerResources) {
        this.bonusVictoryPointFromPlayerResources = bonusVictoryPointFromPlayerResources;
    }
}