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
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Francesco on 25/05/2017.
 */
public class ActionControlSet {
    private final DevelopmentCardRequirementsControl developmentCardRequirementsControl;
    private final DiceValueVsDiceColorControl diceValueVsDiceColorControl;
    private final DiceValueVsCardSpaceControl diceValueVsCardSpaceControl;
    private final OccupiedActionSpaceControl occupiedActionSpaceControl;
    private final PayResourceControl payResourceControl;
    private final PayResourceListControl payResourceListControl;
    private final PlacedFamilyMemberControl placedFamilyMemberControl;
    private final PlayerCardNumberControl playerCardNumberControl;
    private final ResourceRequirementsControl resourceRequirementsControl;
    private final SelfOccupiedTowerControl selfOccupiedTowerControl;
    private final TakeDevelopmentCardControl takeDevelopmentCardControl;
    private final TowerPlacementControl towerPlacementControl;
    private final LeaderCardRequirementControl leaderCardRequirementControl;


    /* Constructor */
    public ActionControlSet(Player player)
    {
        this.resourceRequirementsControl = new ResourceRequirementsControl(player);
        this.diceValueVsDiceColorControl = new DiceValueVsDiceColorControl(player);
        this.diceValueVsCardSpaceControl = new DiceValueVsCardSpaceControl(player);
        this.occupiedActionSpaceControl = new OccupiedActionSpaceControl(player);
        this.payResourceControl = new PayResourceControl(player);
        this.payResourceListControl = new PayResourceListControl(player);
        this.placedFamilyMemberControl = new PlacedFamilyMemberControl(player);
        this.playerCardNumberControl = new PlayerCardNumberControl(player);
        this.towerPlacementControl = new TowerPlacementControl(player);
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
        this.diceValueVsDiceColorControl.setDiceValue(diceValue);
        this.diceValueVsDiceColorControl.setDiceColor(diceColor);
        return this.diceValueVsDiceColorControl.execute();
    }

    public boolean diceValueVsCardSpaceControl (Integer diceValue, TowerCardSpace towerCardSpace)
    {
        this.diceValueVsCardSpaceControl.setDiceValue(diceValue);
        this.diceValueVsCardSpaceControl.setTowerCardSpace(towerCardSpace);
        return this.diceValueVsCardSpaceControl.execute();
    }

    public boolean occupiedActionSpaceControl(ActionSpace actionSpace)
    {
        this.occupiedActionSpaceControl.setActionSpace(actionSpace);
        return this.occupiedActionSpaceControl.execute();
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

    public boolean towerPlacementControl(FamilyMember familyMember, TowerCardSpace towerCardSpace)
    {
        this.towerPlacementControl.setFamilyMember(familyMember);
        this.towerPlacementControl.setTowerCardSpace(towerCardSpace);
        return this.towerPlacementControl.execute();
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

    public DiceValueVsDiceColorControl getDiceValueVsDiceColorControl() {
        return diceValueVsDiceColorControl;
    }

    public DiceValueVsCardSpaceControl getDiceValueVsCardSpaceControl() {
        return diceValueVsCardSpaceControl;
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

    public TowerPlacementControl getTowerPlacementControl() {
        return towerPlacementControl;
    }


}
