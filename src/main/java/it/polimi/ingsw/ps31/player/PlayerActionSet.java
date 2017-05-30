package it.polimi.ingsw.ps31.player;

import it.polimi.ingsw.ps31.actions.*;
import it.polimi.ingsw.ps31.board.ActionSpace;
import it.polimi.ingsw.ps31.constants.CardColor;
import it.polimi.ingsw.ps31.constants.DiceColor;
import it.polimi.ingsw.ps31.gameThings.Resource;
import it.polimi.ingsw.ps31.gameThings.ResourceList;

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
    private final ActionTakeCard takeCard;
    private final ActiveLeaderCard activeLeaderCard;
    private final ActionGetFinalResources getFinalResources;
    private final ActionControlSet actionControlSet;
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
        this.takeCard = new ActionTakeCard(player, actionControlSet);
        this.activeLeaderCard = new ActiveLeaderCard(player, actionControlSet);
        this.getFinalResources = new ActionGetFinalResources(player, actionControlSet);
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

    /* Class Methods */
    public void activateHarvest(int diceValue)
    {
        //TODO:settare i parametri
        this.activateHarvest.activate();
    }

    public void activateProduction(int diceValue)
    {
        //TODO:settare i parametri
        this.activateProduction.activate();
    }

    public void chooseCard(CardColor cardColor, int diceCost, int diceDiscount)
    {
        this.chooseCard.setCardColor(cardColor);
        this.chooseCard.setDiceCost(diceCost);
        this.chooseCard.setDiceDiscount(diceDiscount);
        this.chooseCard.activate();
    }

    public void chooseDifferentPrivilege(int numOfPrivileges, boolean isDifferent)
    {
        //TODO: implementare
    }

    public void getResources(ResourceList resourcesToGet)
    {
        this.getResources.setResourcesToGet(resourcesToGet);
        this.getResources.activate();
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

    public void payServants(DiceColor color, int servantsAmount)
    {
        this.payServants.setServantsAmount(servantsAmount);
        this.payServants.setDiceColor(color);
        this.payServants.activate();
    }

    public void payTowerMoney()
    {
        this.payTowerMoney.activate();
    }

    public void payTowerMoney(int payAmount)
    {
        this.payTowerMoney.setCoinToPay(payAmount);
        this.payTowerMoney.activate();
    }

    public void placeFamilyMemberInTower(FamilyMember familyMember, ActionSpace actionSpace)
    {
        this.placeFamilyMemberInTower.setFamilyMember(familyMember);
        this.placeFamilyMemberInTower.setTowerActionSpace(actionSpace);
        this.placeFamilyMemberInTower.activate();
    }

    public void placeFamilyMemberInBoard(FamilyMember familyMember, ActionSpace actionSpace)
    {
        this.placeFamilyMemberInBoard.setFamilyMember(familyMember);
        this.placeFamilyMemberInBoard.setTowerActionSpace(actionSpace);
        this.placeFamilyMemberInBoard.activate();
    }

    public void getFinalResources()
    {
        this.getFinalResources.setResourcesToGet(player.getFinalBonusResources());
        this.getFinalResources.activate();
    }

    public void addFinalVictoryPoints()
    {
        //TODO: aggiornare
    }

    public void takeCard()
    {
        //TODO: settare i parametri
        this.takeCard.activate();
    }

    public void activeLeaderCard()
    {
        //TODO: settare i parametri
        this.activeLeaderCard.activate();
    }
}
