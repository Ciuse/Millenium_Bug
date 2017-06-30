package it.polimi.ingsw.ps31.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.client.view.guiView.GuiView;
import it.polimi.ingsw.ps31.messages.GenericMessage;
import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.messages.messageMV.MVVisitable;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.NetworkingMessage;
import it.polimi.ingsw.ps31.messages.messageNetworking.ViewMessage;
import it.polimi.ingsw.ps31.messages.messageVC.*;
import it.polimi.ingsw.ps31.model.actionControls.*;
import it.polimi.ingsw.ps31.model.actions.*;
import it.polimi.ingsw.ps31.model.board.*;
import it.polimi.ingsw.ps31.model.bonus.*;
import it.polimi.ingsw.ps31.model.card.*;
import it.polimi.ingsw.ps31.model.card.Character;
import it.polimi.ingsw.ps31.model.choiceType.*;
import it.polimi.ingsw.ps31.model.effect.*;
import it.polimi.ingsw.ps31.model.gameResource.*;
import it.polimi.ingsw.ps31.model.json.RuntimeTypeAdapterFactory;
import it.polimi.ingsw.ps31.model.player.HarvestList;
import it.polimi.ingsw.ps31.model.player.HarvestProductionList;
import it.polimi.ingsw.ps31.model.player.PersonalBoardCardCell;
import it.polimi.ingsw.ps31.model.player.ProductionList;
import it.polimi.ingsw.ps31.model.stateModel.*;

/**
 * Created by Giuseppe on 27/06/2017.
 */
public class JsonNetworking {


    public static Gson networkingBuilder(){

        RuntimeTypeAdapterFactory<GenericMessage> genericMessageAdapterFactory = RuntimeTypeAdapterFactory.of(GenericMessage.class, "GenericMessage");
        genericMessageAdapterFactory.registerSubtype(NetworkingMessage.class, "NetworkingMessage");
        genericMessageAdapterFactory.registerSubtype(MVVisitable.class, "MVVisitable");
        genericMessageAdapterFactory.registerSubtype(VCVisitable.class, "VCVisitable");


        RuntimeTypeAdapterFactory<NetworkingMessage> networkingMessageAdapterFactory = RuntimeTypeAdapterFactory.of(NetworkingMessage.class, "NetworkingMessage");
        networkingMessageAdapterFactory.registerSubtype(ConnectionMessage.class, "ConnectionMessage");
        networkingMessageAdapterFactory.registerSubtype(ViewMessage.class, "ViewMessage");


        RuntimeTypeAdapterFactory<MVVisitable> mVVisitableAdapterFactory = RuntimeTypeAdapterFactory.of(MVVisitable.class, "MVVisitable");
        mVVisitableAdapterFactory.registerSubtype(MVAskChoice.class, "MVAskChoice");
        mVVisitableAdapterFactory.registerSubtype(MVStringToPrint.class, "MVStringToPrint");
        mVVisitableAdapterFactory.registerSubtype(MVUpdateState.class, "MVUpdateState");


        RuntimeTypeAdapterFactory<VCVisitable> vCVisitableAdapterFactory = RuntimeTypeAdapterFactory.of(VCVisitable.class, "VCVisitable");
        vCVisitableAdapterFactory.registerSubtype(VCActionSpace.class, "VCActionSpace");
        vCVisitableAdapterFactory.registerSubtype(VCActiveEffectChoice.class, "VCActiveEffectChoice");
        vCVisitableAdapterFactory.registerSubtype(VCColorChoice.class, "VCColorChoice");
        vCVisitableAdapterFactory.registerSubtype(VCCouncilPrivilegeChoice.class, "VCCouncilPrivilegeChoice");
        vCVisitableAdapterFactory.registerSubtype(VCFamilyMemberChoice.class, "VCFamilyMemberChoice");
        vCVisitableAdapterFactory.registerSubtype(VCLeaderToActiveChoice.class, "VCLeaderToActiveChoice");
        vCVisitableAdapterFactory.registerSubtype(VCLeaderToDiscardChoice.class, "VCLeaderToDiscardChoice");
        vCVisitableAdapterFactory.registerSubtype(VCListToPayChoice.class, "VCListToPayChoice");
        vCVisitableAdapterFactory.registerSubtype(VCPersonalTilesChoice.class, "VCPersonalTilesChoice");
        vCVisitableAdapterFactory.registerSubtype(VCPlayerAction.class, "VCPlayerAction");
        vCVisitableAdapterFactory.registerSubtype(VCServantToPayChoice.class, "VCServantToPayChoice");
        vCVisitableAdapterFactory.registerSubtype(VCStartLeaderChoice.class, "VCStartLeaderChoice");
        vCVisitableAdapterFactory.registerSubtype(VCSupportTheChurchChoice.class, "VCSupportTheChurchChoice");
        vCVisitableAdapterFactory.registerSubtype(VCTowerCardSpaceChoice.class, "VCTowerCardSpaceChoice");


        RuntimeTypeAdapterFactory<View> viewAdapterFactory = RuntimeTypeAdapterFactory.of(View.class, "View");
        viewAdapterFactory.registerSubtype(CmdLineView.class, "CmdLineView");
        viewAdapterFactory.registerSubtype(GuiView.class, "GuiView");


        RuntimeTypeAdapterFactory<ChoiceType> choiceTypeAdapterFactory = RuntimeTypeAdapterFactory.of(ChoiceType.class, "ChoiceType");
        choiceTypeAdapterFactory.registerSubtype(ChoiceActionSpace.class, "ChoiceActionspace");
        choiceTypeAdapterFactory.registerSubtype(ChoiceActionToDo.class, "ChoiceActionToDo");
        choiceTypeAdapterFactory.registerSubtype(ChoiceColor.class, "ChoiceColor");
        choiceTypeAdapterFactory.registerSubtype(ChoiceFamilyMember.class, "ChoiceFamilyMember");
        choiceTypeAdapterFactory.registerSubtype(ChoiceIfActiveEffect.class, "ChoiceIfActiveEffect");
        choiceTypeAdapterFactory.registerSubtype(ChoiceIfSupportTheChurch.class, "ChoiceIfSupportTheChurch");
        choiceTypeAdapterFactory.registerSubtype(ChoiceLeaderEffectToCopy.class, "ChoiceLeaderEffectToCopy");
        choiceTypeAdapterFactory.registerSubtype(ChoiceLeaderToActive.class, "ChoiceLeaderToActive");
        choiceTypeAdapterFactory.registerSubtype(ChoiceLeaderToDiscard.class, "ChoiceLeaderToDiscard");
        choiceTypeAdapterFactory.registerSubtype(ChoiceListToPay.class, "ChoiceListToPay");
        choiceTypeAdapterFactory.registerSubtype(ChoiceNumberOfServantsToPay.class, "ChoiceNumberOfServantsToPay");
        choiceTypeAdapterFactory.registerSubtype(ChoicePersonalBonusTiles.class, "ChoicePersonalBonusTiles");
        choiceTypeAdapterFactory.registerSubtype(ChoicePrivilegeResource.class, "ChoicePrivilegeResource");
        choiceTypeAdapterFactory.registerSubtype(ChoiceStartLeaderCard.class, "ChoiceStartLeaderCard");
        choiceTypeAdapterFactory.registerSubtype(ChoiceTowerActionSpace.class, "ChoiceTowerActionSpace");
        choiceTypeAdapterFactory.registerSubtype(ChoiceTowerCardSpace.class, "ChoiceTowerCardSpace");


        RuntimeTypeAdapterFactory<Control> controlAdapterFactory = RuntimeTypeAdapterFactory.of(Control.class, "Control");
        controlAdapterFactory.registerSubtype(DevelopmentCardRequirementsControl.class, "DevelopmentCardRequirementsControl");
        controlAdapterFactory.registerSubtype(DiceValueActionSpaceControl.class, "DiceValueActionSpaceControl");
        controlAdapterFactory.registerSubtype(DiceValueCardSpaceControl.class, "DiceValueCardSpaceControl");
        controlAdapterFactory.registerSubtype(LeaderCardRequirementControl.class, "LeaderCardRequirementControl");
        controlAdapterFactory.registerSubtype(OccupiedActionSpaceControl.class, "OccupiedActionSpaceControl");
        controlAdapterFactory.registerSubtype(PayResourceControl.class, "PayResourceControl");
        controlAdapterFactory.registerSubtype(PlacedFamilyMemberControl.class, "PlacedFamilyMemberControl");
        controlAdapterFactory.registerSubtype(PlayerCardNumberControl.class, "PlayerCardNumberControl");
        controlAdapterFactory.registerSubtype(ResourceRequirementsControl.class, "ResourceRequirementsControl");
        controlAdapterFactory.registerSubtype(SelfOccupiedTowerControl.class, "SelfOccupiedTowerControl");
        controlAdapterFactory.registerSubtype(TakeDevelopmentCardControl.class, "TakeDevelopmentCardControl");
        controlAdapterFactory.registerSubtype(TowerCardCostPlacementControl.class, "TowerCardCostPlacementControl");


        RuntimeTypeAdapterFactory<Action> actionAdapterFactory = RuntimeTypeAdapterFactory.of(Action.class, "Action");
        actionAdapterFactory.registerSubtype(ActionActivateHarvest.class, "ActionActivateHarvest");
        actionAdapterFactory.registerSubtype(ActionActivateProduction.class, "ActionActivateProduction");
        actionAdapterFactory.registerSubtype(ActionActiveEndButton.class, "ActionActiveEndButton");
        actionAdapterFactory.registerSubtype(ActionActiveLeaderCard.class, "ActionActiveLeaderCard");
        actionAdapterFactory.registerSubtype(ActionAddFinalBonus.class, "ActionAddFinalBonus");
        actionAdapterFactory.registerSubtype(ActionChooseCard.class, "ActionChooseCard");
        actionAdapterFactory.registerSubtype(ActionChooseDifferentPrivilege.class, "ActionChooseDifferentPrivilege");
        actionAdapterFactory.registerSubtype(ActionDiscardLeaderCard.class, "ActionDiscardLeaderCard");
        actionAdapterFactory.registerSubtype(ActionGetResources.class, "ActionGetResources");
        actionAdapterFactory.registerSubtype(ActionGetTempResourcesFromAllEffect.class, "ActionGetTempResourcesFromAllEffect");
        actionAdapterFactory.registerSubtype(ActionPayCard.class, "ActionPayCard");
        actionAdapterFactory.registerSubtype(ActionPayResources.class, "ActionPayResources");
        actionAdapterFactory.registerSubtype(ActionPayServants.class, "ActionPayServants");
        actionAdapterFactory.registerSubtype(ActionPayTowerMoney.class, "ActionPayTowerMoney");
        actionAdapterFactory.registerSubtype(ActionPlaceFamilyMember.class, "ActionPlaceFamilyMember");


        RuntimeTypeAdapterFactory<ActionPlaceFamilyMember> actionPFMAdapterFactory = RuntimeTypeAdapterFactory.of(ActionPlaceFamilyMember.class, "ActionPlaceFamilyMember");
        actionPFMAdapterFactory.registerSubtype(ActionPlaceFamilyMemberInBoard.class, "ActionPlaceFamilyMemberInBoard");
        actionPFMAdapterFactory.registerSubtype(ActionPlaceFamilyMemberInTower.class, "ActionPlaceFamilyMemberInTower");


        RuntimeTypeAdapterFactory<Harvest> harvestAdapterFactory = RuntimeTypeAdapterFactory.of(Harvest.class, "Harvest");
        harvestAdapterFactory.registerSubtype(SmallHarvest.class, "SmallHarvest");
        harvestAdapterFactory.registerSubtype(BigHarvest.class, "BigHarvest");


        RuntimeTypeAdapterFactory<PhysicalCardBox> physicalCardBoxAdapterFactory = RuntimeTypeAdapterFactory.of(PhysicalCardBox.class, "PhysicalCardBox");
        physicalCardBoxAdapterFactory.registerSubtype(TowerCardSpace.class, "TowerCardSpace");
        physicalCardBoxAdapterFactory.registerSubtype(PersonalBoardCardCell.class, "PersonalBoardCardCell");


        RuntimeTypeAdapterFactory<Production> productionAdapterFactory = RuntimeTypeAdapterFactory.of(Production.class, "Production");
        productionAdapterFactory.registerSubtype(SmallProduction.class, "SmallProduction");
        productionAdapterFactory.registerSubtype(BigProduction.class, "BigProduction");


        RuntimeTypeAdapterFactory<Track> trackAdapterFactory = RuntimeTypeAdapterFactory.of(Track.class, "Track");
        trackAdapterFactory.registerSubtype(FaithPointTrack.class, "FaithPointTrack");
        trackAdapterFactory.registerSubtype(MilitaryPointTrack.class, "MilitaryPointTrack");
        trackAdapterFactory.registerSubtype(VictoryPointTrack.class, "VictoryPointTrack");


        RuntimeTypeAdapterFactory<Bonus> bonusAdapterFactory = RuntimeTypeAdapterFactory.of(Bonus.class, "Bonus");
        bonusAdapterFactory.registerSubtype(CanPlaceInOccupiedActionSpace.class, "CanPlaceInOccupiedActionSpace");
        bonusAdapterFactory.registerSubtype(CantPlaceInActionSpace.class);
        bonusAdapterFactory.registerSubtype(CardDiscountBonus.class);
        bonusAdapterFactory.registerSubtype(ColoredFamilyMembersBonus.class);
        bonusAdapterFactory.registerSubtype(CopyLeaderBonus.class);
        bonusAdapterFactory.registerSubtype(DoubleResourceFromCardBonus.class);
        bonusAdapterFactory.registerSubtype(GainVictoryPointFromLeaderCardBonus.class);
        bonusAdapterFactory.registerSubtype(GetResourceMalus.class);
        bonusAdapterFactory.registerSubtype(HarvestBonus.class);
        bonusAdapterFactory.registerSubtype(LostExtraFinalVictoryPointBonus.class);
        bonusAdapterFactory.registerSubtype(LostFinalVictoryPointBonus.class);
        bonusAdapterFactory.registerSubtype(LostFinalVictoryPointFromCardCosts.class);
        bonusAdapterFactory.registerSubtype(LostFinalVictoryPointFromPlayerResources.class);
        bonusAdapterFactory.registerSubtype(ModifyPayServantsBonus.class);
        bonusAdapterFactory.registerSubtype(NeutralFamilyMemberBonus.class);
        bonusAdapterFactory.registerSubtype(NoBoardRequirementControl.class);
        bonusAdapterFactory.registerSubtype(NoFirstActionTurn.class);
        bonusAdapterFactory.registerSubtype(NoImmediateEffectBonus.class);
        bonusAdapterFactory.registerSubtype(NoTowerPayment.class);
        bonusAdapterFactory.registerSubtype(ProductionBonus.class);
        bonusAdapterFactory.registerSubtype(StaticFamilyMemberValueBonus.class);


        RuntimeTypeAdapterFactory<Card> cardAdapterFactory = RuntimeTypeAdapterFactory.of(Card.class, "Card");
        cardAdapterFactory.registerSubtype(DevelopmentCard.class);
        cardAdapterFactory.registerSubtype(LeaderCard.class);


        RuntimeTypeAdapterFactory<DevelopmentCard> developmentCardAdapterFactory = RuntimeTypeAdapterFactory.of(DevelopmentCard.class, "DevelopmentCard");
        developmentCardAdapterFactory.registerSubtype(Building.class);
        developmentCardAdapterFactory.registerSubtype(Character.class);
        developmentCardAdapterFactory.registerSubtype(Territory.class);
        developmentCardAdapterFactory.registerSubtype(Venture.class);


        RuntimeTypeAdapterFactory<Effect> effectAdapterFactory = RuntimeTypeAdapterFactory.of(Effect.class, "Effect");
        effectAdapterFactory.registerSubtype(BonusAndMalusEffect.class);
        effectAdapterFactory.registerSubtype(ChangeResourceEffect.class);
        effectAdapterFactory.registerSubtype(ChooseCardEffect.class);
        effectAdapterFactory.registerSubtype(GenericHarvestEffectActivation.class);
        effectAdapterFactory.registerSubtype(GenericProductionEffectActivation.class);
        effectAdapterFactory.registerSubtype(GetResourceEffect.class);
        effectAdapterFactory.registerSubtype(HarvestEffect.class);
        effectAdapterFactory.registerSubtype(ProductionEffect.class);


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


        RuntimeTypeAdapterFactory<HarvestProductionList> harvestProductionListAdapterFactory = RuntimeTypeAdapterFactory.of(HarvestProductionList.class, "CardType");
        harvestProductionListAdapterFactory.registerSubtype(HarvestList.class);
        harvestProductionListAdapterFactory.registerSubtype(ProductionList.class);


        RuntimeTypeAdapterFactory<StateType> stateTypeAdapterFactory = RuntimeTypeAdapterFactory.of(StateType.class, "CardType");
        stateTypeAdapterFactory.registerSubtype(StateActionSpace.class);
        stateTypeAdapterFactory.registerSubtype(StateAllFamilyMember.class);
        stateTypeAdapterFactory.registerSubtype(StateCardBox.class);
        stateTypeAdapterFactory.registerSubtype(StateDevelopmentCard.class);
        stateTypeAdapterFactory.registerSubtype(StateEffect.class);
        stateTypeAdapterFactory.registerSubtype(StateFamilyMember.class);
        stateTypeAdapterFactory.registerSubtype(StateGame.class);
        stateTypeAdapterFactory.registerSubtype(StateLeaderCard.class);
        stateTypeAdapterFactory.registerSubtype(StateMarkerDisc.class);
        stateTypeAdapterFactory.registerSubtype(StatePersonalBoard.class);
        stateTypeAdapterFactory.registerSubtype(StatePersonalBonusTiles.class);
        stateTypeAdapterFactory.registerSubtype(StatePlayer.class);
        stateTypeAdapterFactory.registerSubtype(StatePlayerAction.class);
        stateTypeAdapterFactory.registerSubtype(StatePlayerResources.class);
        stateTypeAdapterFactory.registerSubtype(StateTower.class);



        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls().
                registerTypeAdapterFactory(genericMessageAdapterFactory).
                registerTypeAdapterFactory(networkingMessageAdapterFactory).
                registerTypeAdapterFactory(mVVisitableAdapterFactory).
                registerTypeAdapterFactory(vCVisitableAdapterFactory).
                registerTypeAdapterFactory(viewAdapterFactory).
                registerTypeAdapterFactory(choiceTypeAdapterFactory).
                registerTypeAdapterFactory(controlAdapterFactory).
                registerTypeAdapterFactory(actionAdapterFactory).
                registerTypeAdapterFactory(actionPFMAdapterFactory).
                registerTypeAdapterFactory(harvestAdapterFactory).
                registerTypeAdapterFactory(physicalCardBoxAdapterFactory).
                registerTypeAdapterFactory(productionAdapterFactory).
                registerTypeAdapterFactory(trackAdapterFactory).
                registerTypeAdapterFactory(bonusAdapterFactory).
                registerTypeAdapterFactory(cardAdapterFactory).
                registerTypeAdapterFactory(developmentCardAdapterFactory).
                registerTypeAdapterFactory(effectAdapterFactory).
                registerTypeAdapterFactory(resourceAdapterFactory).
                registerTypeAdapterFactory(pointResourceRuntimeTypeAdapterFactory).
                registerTypeAdapterFactory(physicalResourceRuntimeTypeAdapterFactory).
                registerTypeAdapterFactory(harvestProductionListAdapterFactory).
                registerTypeAdapterFactory(stateTypeAdapterFactory);


        Gson gson = builder.create();

        return gson;
    }
}
