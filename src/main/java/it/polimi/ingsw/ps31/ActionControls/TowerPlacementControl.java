package it.polimi.ingsw.ps31.ActionControls;

import it.polimi.ingsw.ps31.Actions.ActionControlSet;
import it.polimi.ingsw.ps31.Board.TowerCardSpace;
import it.polimi.ingsw.ps31.GameThings.ResourceList;
import it.polimi.ingsw.ps31.Player.FamilyMember;
import it.polimi.ingsw.ps31.Player.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 26/05/2017.
 */
public class TowerPlacementControl extends Control {
    private int OCCUPIED_TOWER_COST = 3;        //Costo in monete per poter piazzare un familiare in una torre già occupata
    private TowerCardSpace towerCardSpace = null;
    private FamilyMember familyMember = null;

    /* Setters & Getters */
    public TowerCardSpace getTowerCardSpace()
    {
        return towerCardSpace;
    }

    public void setTowerCardSpace(TowerCardSpace towerCardSpace)
    {
        this.towerCardSpace = towerCardSpace;
    }

    public FamilyMember getFamilyMember()
    {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember)
    {
        this.familyMember = familyMember;
    }

    public void resetFamilyMember()
    {
        this.familyMember = null;
    }

    public void restTowerCardSpace()
    {
        this.towerCardSpace = null;
    }

    /* Constructor */
    public TowerPlacementControl(Player player) {
        super(player);
    }

    /* Class Methods */
    @Override
    public boolean execute()
    {
        //Controllo che i parametri siano settati
        if (this.towerCardSpace == null || this.familyMember == null)
        {
            //TODO: eccezione
            return false; //Altrimenti non compila
        } else
        {
            ActionControlSet controlSet = player.getPlayerActionSet().getActionControlSet();


            //Controllo che il familiare non sia già piazzato
            conditions.add(controlSet.placedFamilyMemberControl(familyMember));

            //Controllo che il cardSpace contenga una carta
            conditions.add(towerCardSpace.getCard() != null);

            //Controllo che il familiare non sarebbe il secondo familiare colorato nella torre
            conditions.add(controlSet.selfOccupiedTowerControl(familyMember, towerCardSpace.getTower()));

            //Controllo che il giocatore abbia le tre monete (se necessario)
            conditions.add(towerCardSpace.getTower().isOccupied() &&
                            player.getResources().getResource("Coin").getValue() >= OCCUPIED_TOWER_COST);

            //Controllo che il valore del dado sia sufficiente per il piazzamento
            conditions.add(familyMember.getTotalValue() >= towerCardSpace.getActionSpace().getDiceCost());

            //Controllo che il giocatore possa pagare il costo della carta
            boolean cardCostAffordable = false;
            if (towerCardSpace.getCard().getCostList() == null)
                cardCostAffordable = true;
            else
                for (ResourceList currentCost : towerCardSpace.getCard().getCostList())
                {
                    if (player.getResources().greaterThan(currentCost))
                        cardCostAffordable = true;
                }
            conditions.add(cardCostAffordable);

            boolean result = true;
            Iterator<Boolean> checkIterator = conditions.iterator();
            while(result && checkIterator.hasNext())
                result = result && checkIterator.next();

            return result;

        }
    }
}
