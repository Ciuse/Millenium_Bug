package it.polimi.ingsw.ps31.Player;

import it.polimi.ingsw.ps31.ActionControls.*;
import it.polimi.ingsw.ps31.Actions.*;
import it.polimi.ingsw.ps31.Board.ActionSpace;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Constants.DiceColor;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

/**
 * Created by Francesco on 24/05/2017.
 */
public class PlayerActionSet {
    private final ActionActivateHarvest activateHarvest;
    private final ActionActivateProduction activateProduction;
    private final ActionChooseCard chooseCard;
    private final ActionChoosePrivilege choosePrivilege;
    private final ActionGetResources getResources;
    private final ActionPayResources payResources;
    private final ActionPayServants payServants;
    private final ActionPayTowerMoney payTowerMoney;
    private final ActionPlaceFamilyMember placeFamilyMember;
    private final ActionTakeCard takeCard;
    private final ActiveLeaderCard activeLeaderCard;
    private final Player player;
    private final ActionControlSet actionControlSet;

    /* Constructor */
    public PlayerActionSet(Player player) {
        this.player = player;

        this.activateHarvest = new ActionActivateHarvest(player);
        this.activateProduction = new ActionActivateProduction(player);
        this.chooseCard = new ActionChooseCard(player);
        this.choosePrivilege = new ActionChoosePrivilege(player);
        this.getResources = new ActionGetResources(player);
        this.payResources = new ActionPayResources(player);
        this.payServants = new ActionPayServants(player);
        this.payTowerMoney = new ActionPayTowerMoney(player);
        this.placeFamilyMember = new ActionPlaceFamilyMember(player);
        this.takeCard = new ActionTakeCard(player);
        this.activeLeaderCard = new ActiveLeaderCard(player);

        this.actionControlSet = new ActionControlSet(player);
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

    public void setChoosePrivilege(int numOfPrivileges)
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

    public void placeFamilyMember(FamilyMember familyMember, ActionSpace actionSpace)
    {
        this.placeFamilyMember.setFamilyMember(familyMember);
        this.placeFamilyMember.setActionSpace(actionSpace);
        this.placeFamilyMember.activate();
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
