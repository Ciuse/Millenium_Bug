package it.polimi.ingsw.ps31.Json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.ps31.Card.*;
import it.polimi.ingsw.ps31.Card.Character;
import it.polimi.ingsw.ps31.Effect.*;
import it.polimi.ingsw.ps31.GameThings.*;
import it.polimi.ingsw.ps31.bonus.*;

import java.util.List;

/**
 * Created by Giuseppe on 11/05/2017.
 */
public class JsonGameObject {
    private DevelopmentCardList developementCardList;
    private List<LeaderCard> leaderCardList;
    private EffectList[] towerActionSpaceEffecArray;
    private List<EffectList> actionSpaceEffectList;
    private int[] faithTrackExtraValue;
    private int[] bonusVictoryPointFromCharacterCard;
    private int[] bonusVictoryPointFromTerritory;
    private int[] requiredMilitaryStrengthForTerritory;
    private int bonusVictoryPointFromPlayerResources;

    public JsonGameObject() {
    }

    public static Gson gsonGameBuilder(){       //TODO assicurarsi che ci siano tutti i sottotipi di queste classi astratte

        //Serve per poter far riconoscere a Json una Lista di classi polimorfe
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
        effectAdapterFactory.registerSubtype(HarvestActivationFromCard.class, "HarvestActivationFromCard");
        effectAdapterFactory.registerSubtype(HarvestEffect.class, "HarvestEffect");
        effectAdapterFactory.registerSubtype(LeaderEffect.class, "LeaderEffect");
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
    public DevelopmentCardList getCardList(){  //ritorno la lista stessa, non mi interessa se la possono modificare

        return this.developementCardList;
    }

    public List<LeaderCard> getLeaderCardList() {
        return leaderCardList;
    }

    public List<EffectList> getActionSpaceEffectList(){  //ritorno la lista stessa, non mi interessa se la possono modificare

        return this.actionSpaceEffectList;
    }

    public int[] getFaithTrackExtraValue() {
        return faithTrackExtraValue;
    }

    public int[] getBonusVictoryPointFromCharacterCard() {
        return bonusVictoryPointFromCharacterCard;
    }

    public int getBonusVictoryPointFromPlayerResources() {
        return bonusVictoryPointFromPlayerResources;
    }

    public int[] getBonusVictoryPointFromTerritory() {
        return bonusVictoryPointFromTerritory;
    }

    public int[] getRequiredMilitaryStrengthForTerritory() {
        return requiredMilitaryStrengthForTerritory;
    }

    public EffectList[] getTowerActionSpaceEffecArray() {
        return towerActionSpaceEffecArray;
    }


    public void setDevelopementCardList(DevelopmentCardList developementCardList) {
        this.developementCardList = developementCardList;
    }

    public void setLeaderCardList(List<LeaderCard> leaderCardList) {
        this.leaderCardList = leaderCardList;
    }

    public void setActionSpaceEffectList(List<EffectList> actionSpaceEffectList) {
        this.actionSpaceEffectList = actionSpaceEffectList;
    }

    public void setFaithTrackExtraValue(int[] faithTrackExtraValue) {
        this.faithTrackExtraValue = faithTrackExtraValue;
    }

    public void setBonusVictoryPointFromCharacterCard(int[] bonusVictoryPointFromCharacterCard) {
        this.bonusVictoryPointFromCharacterCard = bonusVictoryPointFromCharacterCard;
    }

    public void setBonusVictoryPointFromPlayerResources(int bonusVictoryPointFromPlayerResources) {
        this.bonusVictoryPointFromPlayerResources = bonusVictoryPointFromPlayerResources;
    }

    public void setBonusVictoryPointFromTerritory(int[] bonusVictoryPointFromTerritory) {
        this.bonusVictoryPointFromTerritory = bonusVictoryPointFromTerritory;
    }

    public void setTowerActionSpaceEffecArray(EffectList[] towerActionSpaceEffecArray) {
        this.towerActionSpaceEffecArray = towerActionSpaceEffecArray;
    }

    public void setRequiredMilitaryStrengthForTerritory(int[] requiredMilitaryStrengthForTerritory) {
        this.requiredMilitaryStrengthForTerritory = requiredMilitaryStrengthForTerritory;
    }
}