package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.actionControls.*;
import it.polimi.ingsw.ps31.model.board.ActionSpace;
import it.polimi.ingsw.ps31.model.board.Tower;
import it.polimi.ingsw.ps31.model.board.TowerCardSpace;
import it.polimi.ingsw.ps31.model.card.DevelopmentCard;
import it.polimi.ingsw.ps31.model.card.DevelopmentCardList;
import it.polimi.ingsw.ps31.model.card.LeaderCard;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Francesco on 25/05/2017.
 */
public class ActionControlSet {
    private final DevelopmentCardRequirementsControl developmentCardRequirementsControl;
    private final DiceValueActionSpaceControl diceValueActionSpaceControl;
    private final DiceValueCardSpaceControl diceValueCardSpaceControl;
    private final OccupiedActionSpaceControl occupiedActionSpaceControl;
    private final PayResourceControl payResourceControl;
    private final PayResourceListControl payResourceListControl;
    private final PlacedFamilyMemberControl placedFamilyMemberControl;
    private final PlayerCardNumberControl playerCardNumberControl;
    private final ResourceRequirementsControl resourceRequirementsControl;
    private final SelfOccupiedTowerControl selfOccupiedTowerControl;
    private final TakeDevelopmentCardControl takeDevelopmentCardControl;
    private final TowerCardCostPlacementControl towerCardCostPlacementControl;
    private final LeaderCardRequirementControl leaderCardRequirementControl;


    /* Constructor */
    public ActionControlSet(Player player)
    {
        this.resourceRequirementsControl = new ResourceRequirementsControl(player);
        this.diceValueActionSpaceControl = new DiceValueActionSpaceControl(player);
        this.diceValueCardSpaceControl = new DiceValueCardSpaceControl(player);
        this.occupiedActionSpaceControl = new OccupiedActionSpaceControl(player);
        this.payResourceControl = new PayResourceControl(player);
        this.payResourceListControl = new PayResourceListControl(player);
        this.placedFamilyMemberControl = new PlacedFamilyMemberControl(player);
        this.playerCardNumberControl = new PlayerCardNumberControl(player);
        this.towerCardCostPlacementControl = new TowerCardCostPlacementControl(player);
        this.selfOccupiedTowerControl= new SelfOccupiedTowerControl(player);
        this.developmentCardRequirementsControl = new DevelopmentCardRequirementsControl(player);
        this.takeDevelopmentCardControl = new TakeDevelopmentCardControl(player);
        this.leaderCardRequirementControl= new LeaderCardRequirementControl(player);
    }

    /* Class Methods */
    public boolean developmentCardRequirementsControl(DevelopmentCardList requirements){
        this.developmentCardRequirementsControl.setRequirements(requirements);
        return this.developmentCardRequirementsControl.execute();
    }
    public boolean resourceRequirementsControl(ResourceList requirements)
    {
        this.resourceRequirementsControl.setRequirements(requirements);
        return this.resourceRequirementsControl.execute();
    }

    public boolean diceValueVsDiceColorControl(Integer diceValue, DiceColor diceColor)
    {
        this.diceValueActionSpaceControl.setDiceValue(diceValue);
        this.diceValueActionSpaceControl.setDiceColor(diceColor);
        return this.diceValueActionSpaceControl.execute();
    }

    public boolean diceValueVsCardSpaceControl (Integer diceValue, TowerCardSpace towerCardSpace)
    {
        this.diceValueCardSpaceControl.setDiceValue(diceValue);
        this.diceValueCardSpaceControl.setTowerCardSpace(towerCardSpace);
        return this.diceValueCardSpaceControl.execute();
    }

    public boolean occupiedActionSpaceControl(ActionSpace actionSpace)
    {
        this.occupiedActionSpaceControl.setActionSpace(actionSpace);
        return this.occupiedActionSpaceControl.execute();
    }

    public boolean payResourceControl(Resource resource)
    {
        ResourceList resourceList= new ResourceList(resource);
        this.payResourceControl.setResourceList(resourceList);
        return this.payResourceControl.execute();
    }

    public boolean payResourceControl(ResourceList resourceList)
    {
        this.payResourceControl.setResourceList(resourceList);
        return this.payResourceControl.execute();
    }

    public boolean payResourceListControl(List<ResourceList> resourceLists)
    {
        this.payResourceListControl.setResourceLists(resourceLists);
        return this.payResourceListControl.execute();
    }

    public boolean placedFamilyMemberControl(FamilyMember familyMember)
    {
        this.placedFamilyMemberControl.setFamilyMember(familyMember);
        return this.placedFamilyMemberControl.execute();
    }

    public boolean playerCardNumberControl(CardColor cardColor)
    {
        this.playerCardNumberControl.setCardColor(cardColor);
        return this.playerCardNumberControl.execute();
    }

    public boolean towerCostPlacementControl(TowerCardSpace towerCardSpace)
    {
        this.towerCardCostPlacementControl.setTowerCardSpace(towerCardSpace);
        return this.towerCardCostPlacementControl.execute();
    }

    public boolean takeDevelopmentCardControl(DevelopmentCard developmentCard){
        takeDevelopmentCardControl.setDevelopmentCard(developmentCard);
        return this.takeDevelopmentCardControl.execute();
    }

    public boolean selfOccupiedTowerControl(FamilyMember familyMember, Tower tower)
    {
        this.selfOccupiedTowerControl.setFamilyMember(familyMember);
        this.selfOccupiedTowerControl.setTower(tower);
        return this.selfOccupiedTowerControl.execute();
    }

    public boolean leaderCardRequirementControl(LeaderCard leaderCard){
        this.leaderCardRequirementControl.setLeaderCard(leaderCard);
        return this.leaderCardRequirementControl.execute();
    }
    /* Getters */
    public DevelopmentCardRequirementsControl getDevelopmentCardRequirementsControl() {
        return developmentCardRequirementsControl;
    }

    public DiceValueActionSpaceControl getDiceValueActionSpaceControl() {
        return diceValueActionSpaceControl;
    }

    public DiceValueCardSpaceControl getDiceValueCardSpaceControl() {
        return diceValueCardSpaceControl;
    }

    public OccupiedActionSpaceControl getOccupiedActionSpaceControl() {
        return occupiedActionSpaceControl;
    }

    public PayResourceControl getPayResourceControl() {
        return payResourceControl;
    }

    public PayResourceListControl getPayResourceListControl() {
        return payResourceListControl;
    }

    public PlacedFamilyMemberControl getPlacedFamilyMemberControl() {
        return placedFamilyMemberControl;
    }

    public PlayerCardNumberControl getPlayerCardNumberControl() {
        return playerCardNumberControl;
    }

    public ResourceRequirementsControl getResourceRequirementsControl() {
        return resourceRequirementsControl;
    }

    public SelfOccupiedTowerControl getSelfOccupiedTowerControl() {
        return selfOccupiedTowerControl;
    }

    public TakeDevelopmentCardControl getTakeDevelopmentCardControl() {
        return takeDevelopmentCardControl;
    }

    public TowerCardCostPlacementControl getTowerCardCostPlacementControl() {
        return towerCardCostPlacementControl;
    }

    public LeaderCardRequirementControl getLeaderCardRequirementControl() {
        return leaderCardRequirementControl;
    }
}
