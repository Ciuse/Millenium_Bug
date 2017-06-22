package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceNumberOfServantsToPay;
import it.polimi.ingsw.ps31.model.constants.DiceColor;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.gameResource.Servant;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionPayServants extends Action {
    private DiceColor diceColor = null;
    private Integer servantsAmount = null;
    private int servantsToPayPerUnitaryDiceValueArise = 1;

    public ActionPayServants(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    public void setDiceColor(DiceColor diceColor)
    {
        //TODO: Controllare che il familiare sia libero
        this.diceColor = diceColor;
    }

    public void setServantsAmount(Integer servantsAmount)
    {
        this.servantsAmount = servantsAmount;
    }

    /* Resetters */
    public void resetServantsAmount()
    {
        this.servantsAmount = null;
    }

    @Override
    public void activate()
    {
        //Controllo che i parametri siano settati;
        if ( this.servantsAmount == null || this.diceColor == null )
        {
            //TODO: eccezione
        } else
        {
            //Controllo che il familiare per cui si sta pagando non sia già stato piazzato
            boolean condition1 = super.actionControlSet.placedFamilyMemberControl(player.getSpecificFamilyMember(this.diceColor));

            //chiedere al player quanti servitori vuole pagare per aumentare il valore del familiare
            String string = player.getPlayerId() + ":Quanti servitori vuoi pagare per aumentare il valore del tuo familiare?";
            notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceNumberOfServantsToPay()));
            int servantsAmount = super.waitNumberOfServantsToPay();


            //Controllo sul numero di servitori
            List<Resource> servantsAsList = new ArrayList<>();
            Resource servantsAsResource = new Servant(servantsAmount);
            servantsAsList.add(servantsAsResource);
            ResourceList servantsAsResourceList = new ResourceList(servantsAsList);

            boolean condition2 = super.actionControlSet.payResourceControl(servantsAsResourceList);

            if (condition1){
                if(condition2){
                //Eseguo fisicamente l'azione
                    player.subResources(servantsAsResource);
                    player.getSpecificFamilyMember(this.diceColor).addAdditionalValue(Math.floorDiv(servantsAmount, servantsToPayPerUnitaryDiceValueArise));
                }else super.notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getPlacedFamilyMemberControl().getControlStringError()));
            }else super.notifyViews(new MVStringToPrint(player.getPlayerId(), false, super.actionControlSet.getPayResourceControl().getControlStringError()));
            resetServantsAmount();
            super.notifyViews(new MVUpdateState("Aggiornato stato familyMember: "+player.getSpecificFamilyMember(this.diceColor).getDiceColor().name(),
                    player.getSpecificFamilyMember(this.diceColor).getStateFamilyMember()));
            super.notifyViews(new MVUpdateState("Aggiornato stato PlayerResources",player.getStatePlayerResources()));

        }
    }

    public void setServantsPerDiceRise(int servantsToPayPerUnitaryDiceValueArise)
    {
        this.servantsToPayPerUnitaryDiceValueArise = servantsToPayPerUnitaryDiceValueArise;
    }
}

