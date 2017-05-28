package it.polimi.ingsw.ps31.Actions;

import it.polimi.ingsw.ps31.ActionControls.*;
import it.polimi.ingsw.ps31.Board.ActionSpace;
import it.polimi.ingsw.ps31.Board.Tower;
import it.polimi.ingsw.ps31.Board.TowerCardSpace;
import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Constants.DiceColor;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.FamilyMember;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 25/05/2017.
 */
public class ActionControlSet {
    private final CardRequirementsControl cardRequirementsControl;
    private final DiceValueVsDiceColorControl diceValueVsDiceColorControl;
    private final DiceValueVsCardSpaceControl diceValueVsCardSpaceControl;
    private final OccupiedActionSpaceControl occupiedActionSpaceControl;
    private final PayResourceControl payResourceControl;
    private final PayResourceListControl payResourceListControl;
    private final PlacedFamilyMemberControl placedFamilyMemberControl;
    private final PlayerCardNumberControl playerCardNumberControl;
    private final TowerPlacementControl towerPlacementControl;
    private final SelfOccupiedTowerControl selfOccupiedTowerControl;

    /* Constructor */
    public ActionControlSet(Player player)
    {
        this.cardRequirementsControl = new CardRequirementsControl(player);
        this.diceValueVsDiceColorControl = new DiceValueVsDiceColorControl(player);
        this.diceValueVsCardSpaceControl = new DiceValueVsCardSpaceControl(player);
        this.occupiedActionSpaceControl = new OccupiedActionSpaceControl(player);
        this.payResourceControl = new PayResourceControl(player);
        this.payResourceListControl = new PayResourceListControl(player);
        this.placedFamilyMemberControl = new PlacedFamilyMemberControl(player);
        this.playerCardNumberControl = new PlayerCardNumberControl(player);
        this.towerPlacementControl = new TowerPlacementControl(player);
        this.selfOccupiedTowerControl= new SelfOccupiedTowerControl(player);
    }

    /* Class Methods */
    public boolean cardRequirementsControl(ResourceList requirements)
    {
        this.cardRequirementsControl.setRequirements(requirements);
        return (this.cardRequirementsControl.execute());
    }

    public boolean diceValueVsDiceColorControl(Integer diceValue, DiceColor diceColor)
    {
        this.diceValueVsDiceColorControl.setDiceValue(diceValue);
        this.diceValueVsDiceColorControl.setDiceColor(diceColor);
        return (this.diceValueVsDiceColorControl.execute());
    }

    public boolean diceValueVsCardSpaceControl (Integer diceValue, TowerCardSpace towerCardSpace)
    {
        this.diceValueVsCardSpaceControl.setDiceValue(diceValue);
        this.diceValueVsCardSpaceControl.setTowerCardSpace(towerCardSpace);
        return (this.diceValueVsCardSpaceControl.execute());
    }

    public boolean occupiedActionSpaceControl(ActionSpace actionSpace)
    {
        this.occupiedActionSpaceControl.setActionSpace(actionSpace);
        return (this.occupiedActionSpaceControl.execute());
    }

    public boolean payResourceControl(ResourceList resourceList)
    {
        this.payResourceControl.setResourceList(resourceList);
        return (this.payResourceControl.execute());
    }

    public boolean payResourceListControl(List<ResourceList> resourceLists)
    {
        this.payResourceListControl.setResourceLists(resourceLists);
        return (this.payResourceControl.execute());
    }

    public boolean placedFamilyMemberControl(FamilyMember familyMember)
    {
        this.placedFamilyMemberControl.setFamilyMember(familyMember);
        return (this.placedFamilyMemberControl.execute());
    }

    public boolean playerCardNumberControl(CardColor cardColor)
    {
        this.playerCardNumberControl.setCardColor(cardColor);
        return (this.playerCardNumberControl.execute());
    }

    public boolean towerPlacementControl(FamilyMember familyMember, TowerCardSpace towerCardSpace)
    {
        this.towerPlacementControl.setFamilyMember(familyMember);
        this.towerPlacementControl.setTowerCardSpace(towerCardSpace);
        return (this.towerPlacementControl.execute());
    }

    public boolean selfOccupiedTowerControl(FamilyMember familyMember, Tower tower)
    {
        this.selfOccupiedTowerControl.setFamilyMember(familyMember);
        this.selfOccupiedTowerControl.setTower(tower);
        return (this.selfOccupiedTowerControl.execute());
    }
}
