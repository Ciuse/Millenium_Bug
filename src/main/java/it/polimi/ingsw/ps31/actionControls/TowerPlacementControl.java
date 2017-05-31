package it.polimi.ingsw.ps31.actionControls;

import it.polimi.ingsw.ps31.actions.ActionControlSet;
import it.polimi.ingsw.ps31.board.TowerCardSpace;
import it.polimi.ingsw.ps31.gameThings.Coin;
import it.polimi.ingsw.ps31.gameThings.ResourceList;
import it.polimi.ingsw.ps31.player.FamilyMember;
import it.polimi.ingsw.ps31.player.Player;

import java.util.Iterator;

/**
 * Created by Francesco on 26/05/2017.
 */
public class TowerPlacementControl extends Control {
    private int OCCUPIED_TOWER_COST = 3;        //Costo in monete per poter piazzare un familiare in una torre già occupata
    private TowerCardSpace towerCardSpace = null;
    private FamilyMember familyMember = null;

    /* Constructor */
    public TowerPlacementControl(Player player)
    {
        super(player);
    }

    /* Setters & Getters */
    public void setTowerCardSpace(TowerCardSpace towerCardSpace)
    {
        this.towerCardSpace = towerCardSpace;
    }
    public void setFamilyMember(FamilyMember familyMember)
    {
        this.familyMember = familyMember;
    }

    public TowerCardSpace getTowerCardSpace()
    {
        return towerCardSpace;
    }
    public FamilyMember getFamilyMember()
    {
        return familyMember;
    }

    /* Resetters */
    public void restTowerCardSpace()
    {
        this.towerCardSpace = null;
    }
    public void resetFamilyMember()
    {
        this.familyMember = null;
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
                            player.getPlayerResources().getResourceValue(Coin.class) >= OCCUPIED_TOWER_COST);

            //Controllo che il valore del dado sia sufficiente per il piazzamento
            conditions.add(familyMember.getTotalValue() >= towerCardSpace.getActionSpace().getDiceCost());

            //Controllo che il giocatore possa pagare il costo della carta
            boolean cardCostAffordable = false;
            if (towerCardSpace.getCard().getCostList() == null)
                cardCostAffordable = true;
            else
                for (ResourceList currentCost : towerCardSpace.getCard().getCostList())
                {
                    if (player.getPlayerResources().greaterThan(currentCost))
                        cardCostAffordable = true;
                }
            conditions.add(cardCostAffordable);

            boolean result = true;
            Iterator<Boolean> checkIterator = conditions.iterator();
            while(result && checkIterator.hasNext())
                result = checkIterator.next();

            return result;

        }
    }
}
