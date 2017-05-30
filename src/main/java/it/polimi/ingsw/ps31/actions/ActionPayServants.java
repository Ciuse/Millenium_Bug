package it.polimi.ingsw.ps31.actions;

import it.polimi.ingsw.ps31.constants.DiceColor;
import it.polimi.ingsw.ps31.gameThings.Resource;
import it.polimi.ingsw.ps31.gameThings.ResourceList;
import it.polimi.ingsw.ps31.gameThings.Servant;
import it.polimi.ingsw.ps31.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionPayServants extends Action {
    private DiceColor diceColor = null;
    private int servantsAmount = 0;
    private int diceRisePerServant = 1;

    public ActionPayServants(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    public void setDiceColor(DiceColor diceColor)
    {
        //TODO: Controllare che il familiare sia libero
        this.diceColor = diceColor;
    }

    public void setServantsAmount(int servantsAmount)
    {
        this.servantsAmount = servantsAmount;
    }

    @Override
    public void activate()
    {
        //Controllo che i parametri siano settati;
        if ( this.servantsAmount == 0 || this.diceColor == null )
        {
            //TODO: eccezione
        } else
        {
            //Controllo che il familiare per cui si sta pagando non sia già stato piazzato
            boolean condition1 = super.actionControlSet.placedFamilyMemberControl(player.getFamilyMember(this.diceColor));

            //Controllo sul numero di servitori
            List<Resource> servantsAsList = new ArrayList<>();
            Resource servantsAsResource = new Servant(servantsAmount);
            servantsAsList.add(servantsAsResource);
            ResourceList servantsAsResourceList = new ResourceList(servantsAsList);

            boolean condition2 = super.actionControlSet.payResourceControl(servantsAsResourceList);

            if (condition1 && condition2)
            {
                //Eseguo fisicamente l'azione
                player.subResources(servantsAsResource);
                player.getFamilyMember(this.diceColor).addAdditionalValue(servantsAmount * diceRisePerServant);
            } else
            {
                //TODO: eccezione?
            }
        }
    }
}
