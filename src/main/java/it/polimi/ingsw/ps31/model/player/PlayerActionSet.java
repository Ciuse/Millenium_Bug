package it.polimi.ingsw.ps31.model.player;

import it.polimi.ingsw.ps31.model.actions.*;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 24/05/2017.
 */
public class PlayerActionSet {
    private final ActionActivateHarvest activateHarvest;
    private final ActionActivateProduction activateProduction;
    private final ActionChooseCard chooseCard;
    private final ActionChooseDifferentPrivilege chooseDifferentPrivilege;
    private final ActionGetResources getResources;
    private final ActionPayResources payResources;
    private final ActionPayServants payServants;
    private final ActionPayTowerMoney payTowerMoney;
    private final ActionPlaceFamilyMemberInTower placeFamilyMemberInTower;
    private final ActionPlaceFamilyMemberInBoard placeFamilyMemberInBoard;
    private final ActionActiveLeaderCard actionActiveLeaderCard;
    private final ActionDiscardLeaderCard discardLeaderCard;
    private final ActionGetFinalResources getFinalResources;
    private final ActionControlSet actionControlSet;
    private final ActionActiveEndButton activeEndButton;
    private final ActionAddFinalBonus addFinalBonus;
    private final ActionGetTempResourcesFromAllEffect getTempResources;
    private final ActionPayCard payCard;
    private final List<Action> actionList=new ArrayList<>();
    private final Player player;

    /* Constructor */
    public PlayerActionSet(Player player) {
        this.player = player;

        this.actionControlSet = new ActionControlSet(player);
        this.activateHarvest = new ActionActivateHarvest(player, actionControlSet);
        this.activateProduction = new ActionActivateProduction(player, actionControlSet);
        this.chooseCard = new ActionChooseCard(player, actionControlSet);
        this.chooseDifferentPrivilege = new ActionChooseDifferentPrivilege(player, actionControlSet);
        this.getResources = new ActionGetResources(player, actionControlSet);
        this.payResources = new ActionPayResources(player, actionControlSet);
        this.payServants = new ActionPayServants(player, actionControlSet);
        this.payTowerMoney = new ActionPayTowerMoney(player, actionControlSet);
        this.placeFamilyMemberInTower = new ActionPlaceFamilyMemberInTower(player, actionControlSet);
        this.placeFamilyMemberInBoard = new ActionPlaceFamilyMemberInBoard(player, actionControlSet);
        this.actionActiveLeaderCard = new ActionActiveLeaderCard(player, actionControlSet);
        this.discardLeaderCard = new ActionDiscardLeaderCard(player, actionControlSet);
        this.getFinalResources = new ActionGetFinalResources(player, actionControlSet);
        this.activeEndButton= new ActionActiveEndButton(player, actionControlSet); //TODO IMPLEMENTARLO
        this.addFinalBonus= new ActionAddFinalBonus(player, actionControlSet);
        this.getTempResources = new ActionGetTempResourcesFromAllEffect(player,actionControlSet);
        this.payCard= new ActionPayCard(player,actionControlSet);

        addActionToTheList();
    }

    /* Setters & Getters */
    public Player getPlayer()
    {
        return this.player;
    }
    public ActionControlSet getActionControlSet()
    {
        return this.actionControlSet;
    }
    public ActionGetTempResourcesFromAllEffect getGetTempResources() {
        return getTempResources;
    }

    /* Class Methods */
    public void addFinalBonus(ResourceList resourceList){
        this.addFinalBonus.setResourceList(resourceList);
        this.addFinalBonus.activate();
    }
    public void activateHarvest(int diceValue)
    {
        this.activateHarvest.setDiceValue(diceValue);
        this.activateHarvest.activate();
    }

    public void activateProduction(int diceValue)
    {
        this.activateProduction.setDiceValue(diceValue);
        this.activateProduction.activate();
    }

    public void payCard(DevelopmentCard card, ResourceList resourceList){
        this.payCard.setCardToPay(card);
        this.payCard.setResourceListDiscount(resourceList);
        this.payCard.activate();
    }

    public void chooseCard(CardColor cardColor, int diceCost,boolean anyCardColor,ResourceList resourceDiscount)
    {
        this.chooseCard.setCardColor(cardColor);
        this.chooseCard.setDiceCost(diceCost);
        this.chooseCard.setAnyCardColor(anyCardColor);
        this.chooseCard.setResourceDiscount(resourceDiscount);
        this.chooseCard.activate();
    }

    public void chooseCard(CardColor cardColor, int diceCost,boolean anyCardColor)
    {
        this.chooseCard.setCardColor(cardColor);
        this.chooseCard.setDiceCost(diceCost);
        this.chooseCard.setAnyCardColor(anyCardColor);
        this.chooseCard.activate();
    }

    public void chooseDifferentPrivilege(int numOfPrivileges, boolean isDifferent)
    {
        this.chooseDifferentPrivilege.setNumberOfDifferentPrivileges(numOfPrivileges);
        this.chooseDifferentPrivilege.setAreDifferent(isDifferent);
        this.chooseDifferentPrivilege.activate();
    }

    public void activeEndButton(){
        //todo implementare
        this.getActiveEndButton().activate();
    }

    public void getResources(ResourceList resourcesToGet)
    {
        this.getResources.setResourcesToGet(resourcesToGet);
        this.getResources.activate();
    }

    public void getTempResources(ResourceList tempResources){
        this.getTempResources.setResourcesTempToGet(tempResources);
        this.getTempResources.activate();
    }

    public void payResources(ResourceList resourcesToPay)
    {
        this.payResources.setResourceToPay(resourcesToPay);
        this.payResources.activate();
    }

    public void payResources(Resource resourceToPay)
    {
        List<Resource> paymentAsList = new ArrayList<>();
        paymentAsList.add(resourceToPay);

        this.payResources.setResourceToPay(new ResourceList(paymentAsList));
        this.payResources.activate();
    }

    public void payServants(FamilyMember familyMember){
        this.payServants.setFamilyMember(familyMember);
        this.payServants.activate();
    }

    public void payTowerMoney()
    {
        this.payTowerMoney.activate();
    }

    public void placeFamilyMemberInTower()
    {
        this.placeFamilyMemberInTower.activate();
    }

    public void placeFamilyMemberInBoard()
    {
        this.placeFamilyMemberInBoard.activate();
    }

    public void getFinalResources()
    {
        this.getFinalResources.setResourcesToGet(player.getFinalBonusResources());
        this.getFinalResources.activate();
    }

    public void activeLeaderCard()
    {
        this.actionActiveLeaderCard.activate();
    }

    public void discardLeaderCard(){
        this.discardLeaderCard.activate();
    }

    /*Getter*/

    public ActionActivateHarvest getActivateHarvest() {
        return activateHarvest;
    }

    public ActionActivateProduction getActivateProduction() {
        return activateProduction;
    }

    public ActionChooseCard getChooseCard() {
        return chooseCard;
    }

    public ActionChooseDifferentPrivilege getChooseDifferentPrivilege() {
        return chooseDifferentPrivilege;
    }

    public ActionGetResources getGetResources() {
        return getResources;
    }

    public ActionPayResources getPayResources() {
        return payResources;
    }

    public ActionPayServants getPayServants() {
        return payServants;
    }

    public ActionPayTowerMoney getPayTowerMoney() {
        return payTowerMoney;
    }

    public ActionPlaceFamilyMemberInTower getPlaceFamilyMemberInTower() {
        return placeFamilyMemberInTower;
    }

    public ActionPlaceFamilyMemberInBoard getPlaceFamilyMemberInBoard() {
        return placeFamilyMemberInBoard;
    }

    public ActionActiveLeaderCard getActiveLeaderCard() {
        return actionActiveLeaderCard;
    }

    public ActionGetFinalResources getGetFinalResources() {
        return getFinalResources;
    }

    public ActionActiveEndButton getActiveEndButton() {
        return activeEndButton;
    }

    public ActionAddFinalBonus getAddFinalBonus() {
        return addFinalBonus;
    }

    public ActionActiveLeaderCard getActionActiveLeaderCard() {
        return actionActiveLeaderCard;
    }

    public ActionPayCard getPayCard() {
        return payCard;
    }

    public ActionDiscardLeaderCard getDiscardLeaderCard() {
        return discardLeaderCard;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    public void resetUsedAction(){
        this.getPlaceFamilyMemberInBoard().setUsed(false);
        this.getPlaceFamilyMemberInTower().setUsed(false);
    }

    public void addActionToTheList(){
        actionList.add(this.actionActiveLeaderCard);
        actionList.add(this.discardLeaderCard);
        actionList.add(this.placeFamilyMemberInBoard);
        actionList.add(this.placeFamilyMemberInTower);
        actionList.add(this.activeEndButton);
    }
}
