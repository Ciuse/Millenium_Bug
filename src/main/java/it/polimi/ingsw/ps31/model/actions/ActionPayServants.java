package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceNumberOfServantsToPay;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.gameResource.Servant;
import it.polimi.ingsw.ps31.model.player.FamilyMember;
import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionPayServants extends Action {
    private FamilyMember familyMember = null;
    private Integer servantsAmount = null;
    private int servantsToPayPerUnitaryDiceValueArise = 1;

    public ActionPayServants(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
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
        if (this.familyMember == null )
        {
            player.getModel().notifyViews(new MVStringToPrint(player.getPlayerId(),false,"pay servant è stata richiamata senza un servitore su cui agire"));
        } else {

            if (player.getPlayerResources().getSpecificResource(Servant.class).getValue() > 0) {
                //chiedere al player quanti servitori vuole pagare per aumentare il valore del familiare
                do {
                    String string = "Quanti servitori vuoi pagare per aumentare il valore del tuo familiare?";
                    player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoiceNumberOfServantsToPay()));
                    setServantsAmount(player.getModel().getModelChoices().waitNumberOfServantsToPay());

                    if(servantsAmount==-1){ //TIMER SCADUTO
                        player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));
                        servantsAmount=0;
                        break;
                    }
                }
                while (Math.floorDiv(servantsAmount, servantsToPayPerUnitaryDiceValueArise) + familyMember.getTotalValue() <= 0);

                Resource servantToPay = new Servant(servantsAmount); //creo la risorsa servitore con il valore che mi ha detto il giocatore

                //Eseguo fisicamente l'azione
                player.subResources(servantToPay);
                familyMember.addAdditionalValue(Math.floorDiv(servantsAmount, servantsToPayPerUnitaryDiceValueArise));


                //agiorno stato oggetti modificati
                player.getModel().notifyViews(new MVUpdateState("Aggiornato stato familyMember: " + familyMember.getDiceColor().name(), familyMember.getStateFamilyMember()));
                player.getModel().notifyViews(new MVUpdateState("Aggiornato stato PlayerResources", player.getStatePlayerResources()));

                resetServantsAmount();
            }
        }
    }

    public void setServantsPerDiceRise(int servantsToPayPerUnitaryDiceValueArise)
    {
        this.servantsToPayPerUnitaryDiceValueArise = servantsToPayPerUnitaryDiceValueArise;
    }
}

