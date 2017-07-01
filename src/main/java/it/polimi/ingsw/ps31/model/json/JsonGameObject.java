package it.polimi.ingsw.ps31.model.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.ps31.model.bonus.*;
import it.polimi.ingsw.ps31.model.card.*;
import it.polimi.ingsw.ps31.model.card.Character;
import it.polimi.ingsw.ps31.model.effect.*;
import it.polimi.ingsw.ps31.model.gameResource.*;
import it.polimi.ingsw.ps31.model.player.PersonalBonusTiles;

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
    private List<PointResource[]> pointResourceRequired;
    private VictoryPoint bonusVictoryPointFromPlayerResources;
    private List<ResourceList> initialResourcePlayer;
    private List<ExcommunicationTiles> excommunicationTiles;
    private List<PersonalBonusTiles> personalBonusTilesList;
    private List<ResourceList> councilPrivilegeResChoice;
    private long playerConnectionTimer;
    private long playerActionTimer;

    public JsonGameObject() {
    }

        public static Gson gsonGameBuilder() {       //TODO assicurarsi che ci siano tutti i sottotipi di queste classi astratte

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


            RuntimeTypeAdapterFactory<PointResource> pointResourceRuntimeTypeAdapterFactory= RuntimeTypeAdapterFactory.of(PointResource.class,"PointResource");
            pointResourceRuntimeTypeAdapterFactory.registerSubtype(FaithPoint.class, "FaithPoint");
            pointResourceRuntimeTypeAdapterFactory.registerSubtype(MilitaryStrength.class, "MilitaryStrength");
            pointResourceRuntimeTypeAdapterFactory.registerSubtype(VictoryPoint.class, "VictoryPoint");


            RuntimeTypeAdapterFactory<PhysicalResource> physicalResourceRuntimeTypeAdapterFactory= RuntimeTypeAdapterFactory.of(PhysicalResource.class,"PhysicalResource");
            physicalResourceRuntimeTypeAdapterFactory.registerSubtype(Coin.class, "Coin");
            physicalResourceRuntimeTypeAdapterFactory.registerSubtype(Servant.class, "Servant");
            physicalResourceRuntimeTypeAdapterFactory.registerSubtype(Wood.class, "Wood");
            physicalResourceRuntimeTypeAdapterFactory.registerSubtype(Stone.class, "Stone");


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
            effectAdapterFactory.registerSubtype(GenericHarvestEffectActivation.class, "GenericHarvestEffectActivation");
            effectAdapterFactory.registerSubtype(GenericProductionEffectActivation.class, "GenericProductionEffectActivation");
            effectAdapterFactory.registerSubtype(GetResourceEffect.class, "GetResourceEffect");
            effectAdapterFactory.registerSubtype(GetResourceEffectFromCard.class, "GetResourceEffectFromCard");
            effectAdapterFactory.registerSubtype(GetResourceFromResourceEffect.class, "GetResourceFromResourceEffect");
            effectAdapterFactory.registerSubtype(GetResourcesAtTheEndEffect.class, "GetResourcesAtTheEndEffect");
            effectAdapterFactory.registerSubtype(HarvestEffectActivationFromCard.class, "HarvestEffectActivationFromCard");
            effectAdapterFactory.registerSubtype(HarvestEffect.class, "HarvestEffect");
            effectAdapterFactory.registerSubtype(ProductionEffectActivationFromCard.class, "ProductionEffectActivationFromCard");
            effectAdapterFactory.registerSubtype(ProductionEffect.class, "ProductionEffect");


            RuntimeTypeAdapterFactory<Bonus> bonusAdapterFactory = RuntimeTypeAdapterFactory.of(Bonus.class, "BonusType");
            bonusAdapterFactory.registerSubtype(NeutralFamilyMemberBonus.class, "NeutralFamilyMemberBonus");
            bonusAdapterFactory.registerSubtype(CanPlaceInOccupiedActionSpace.class, "CanPlaceInOccupiedActionSpace");
            bonusAdapterFactory.registerSubtype(CantPlaceInActionSpace.class, "CantPlaceInActionSpace");
            bonusAdapterFactory.registerSubtype(CardDiscountBonus.class, "CardDiscountBonus");
            bonusAdapterFactory.registerSubtype(ColoredFamilyMembersBonus.class, "ColoredFamilyMembersBonus");
            bonusAdapterFactory.registerSubtype(CopyLeaderBonus.class, "CopyLeaderBonus");
            bonusAdapterFactory.registerSubtype(StaticFamilyMemberValueBonus.class, "StaticFamilyMemberValueBonus");
            bonusAdapterFactory.registerSubtype(GainVictoryPointFromLeaderCardBonus.class, "GainVictoryPointFromLeaderCardBonus");
            bonusAdapterFactory.registerSubtype(LostExtraFinalVictoryPointBonus.class, "LostExtraFinalVictoryPointBonus");
            bonusAdapterFactory.registerSubtype(LostFinalVictoryPointBonus.class, "LostFinalVictoryPointBonus");
            bonusAdapterFactory.registerSubtype(LostFinalVictoryPointFromCardCosts.class, "LostFinalVictoryPointFromCardCosts");
            bonusAdapterFactory.registerSubtype(LostFinalVictoryPointFromPlayerResources.class, "LostFinalVictoryPointFromPlayerResources");
            bonusAdapterFactory.registerSubtype(ModifyPayServantsBonus.class, "ModifyPayServantsBonus");
            bonusAdapterFactory.registerSubtype(HarvestBonus.class, "HarvestBonus");
            bonusAdapterFactory.registerSubtype(NoImmediateEffectBonus.class, "NoImmediateEffectBonus");
            bonusAdapterFactory.registerSubtype(NoTowerPayment.class, "NoTowerPayment");
            bonusAdapterFactory.registerSubtype(NoBoardRequirementControl.class, "NoBoardRequirementControl");
            bonusAdapterFactory.registerSubtype(NoFirstActionTurn.class,"NoFirstActionTurn");
            bonusAdapterFactory.registerSubtype(ProductionBonus.class, "ProductionBonus");
            bonusAdapterFactory.registerSubtype(GetResourceMalus.class, "GetResourceMalus");


//Creazione del builder adatto a riconoscere tutti gli oggetti polimorfi
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting().serializeNulls()
                    .registerTypeAdapterFactory(resourceAdapterFactory)
                    .registerTypeAdapterFactory(developementCardAdapterFactory)
                    .registerTypeAdapterFactory(effectAdapterFactory)
                    .registerTypeAdapterFactory(bonusAdapterFactory)
                    .registerTypeAdapterFactory(pointResourceRuntimeTypeAdapterFactory)
                    .registerTypeAdapterFactory(physicalResourceRuntimeTypeAdapterFactory);

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
//
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

    public List<PointResource[]> getPointResourceRequired() {
        return pointResourceRequired;
    }

    public void setPointResourceRequired(List<PointResource[]> pointResourceRequired) {
        this.pointResourceRequired = pointResourceRequired;
    }

    public VictoryPoint getBonusVictoryPointFromPlayerResources() {
        return bonusVictoryPointFromPlayerResources;
    }

    public void setBonusVictoryPointFromPlayerResources(VictoryPoint bonusVictoryPointFromPlayerResources) {
        this.bonusVictoryPointFromPlayerResources = bonusVictoryPointFromPlayerResources;
    }

    public List<ResourceList> getInitialResourcePlayer() {
        return new ArrayList<>(initialResourcePlayer);
    }

    public void setInitialResourcePlayer(List<ResourceList> initialResourcePlayer) {
        this.initialResourcePlayer = initialResourcePlayer;
    }

    public List<ExcommunicationTiles> getExcommunicationTiles() {
        return excommunicationTiles;
    }

    public void setExcommunicationTiles(List<ExcommunicationTiles> excommunicationTiles) {
        this.excommunicationTiles = excommunicationTiles;
    }

    public List<PersonalBonusTiles> getPersonalBonusTilesList() {
        return new ArrayList<>(personalBonusTilesList);
    }

    public void setPersonalBonusTilesList(List<PersonalBonusTiles> personalBonusTilesList) {
        this.personalBonusTilesList = personalBonusTilesList;
    }

    public List<ResourceList> getCouncilPrivilegeResChoice() {
        return councilPrivilegeResChoice;
    }

    public void setCouncilPrivilegeResChoice(List<ResourceList> councilPrivilegeResChoice) {
        this.councilPrivilegeResChoice = councilPrivilegeResChoice;
    }

    public long getPlayerConnectionTimer() {
        return playerConnectionTimer;
    }

    public void setPlayerConnectionTimer(long playerConnectionTimer) {
        this.playerConnectionTimer = playerConnectionTimer;
    }

    public long getPlayerActionTimer() {
        return playerActionTimer;
    }

    public void setPlayerActionTimer(long playerActionTimer) {
        this.playerActionTimer = playerActionTimer;
    }
}
