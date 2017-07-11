package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVStringToPrint;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.choiceType.ChoicePrivilegeResource;
import it.polimi.ingsw.ps31.model.gameResource.*;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 18/05/2017.
 *
 * Azione che viene attivata ogni volta che si ottiene un privilegio del consiglio.
 * Necessita un numero dei privilegi e un booleano: se differenti o no
 *
 * @see ActionGetTempResourcesFromAllEffect
 * @see it.polimi.ingsw.ps31.model.stateModel.LastModelStateForControl
 */
public class ActionChooseDifferentPrivilege extends Action {
    private int numberOfDifferentPrivileges;
    private Boolean areDifferent = null;
    private final List<ResourceList> choices = new ArrayList<>();
    private boolean actionTimerEnded = false;

    /* Constructor */
    public ActionChooseDifferentPrivilege(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);

        //Creo la lista di possibili scelte per i privilegi
        choices.add(new ResourceList(new Wood(1), new Stone(1)));
        choices.add(new ResourceList(new Servant(2)));
        choices.add(new ResourceList(new Coin(2)));
        choices.add(new ResourceList(new MilitaryStrength(2)));
        choices.add(new ResourceList(new FaithPoint(1)));
        ;

    }

    /* Setters & Getters */
    public void setAreDifferent(boolean areDifferent) {
        this.areDifferent = areDifferent;
    }

    public void setNumberOfDifferentPrivileges(int numberOfDifferentPrivileges) {
        this.numberOfDifferentPrivileges = numberOfDifferentPrivileges;
    }

    public void resetAreDifferent() {
        this.areDifferent = null;
    }

    public void resetNumberOfDifferentPrivileges() {
        this.numberOfDifferentPrivileges = 0;
    }

    /**
     * Viene creata una lista con i possibili privilegi rimasti da poter scegliere, e si invia la richiesta al player
     * contenente la lista delle risorse che potrà ottenere. Se vi sono più privilegi differenti tra di loro ad ogni
     * ciclio la lista sarà composta dalla lista base meno la lista scelta dal giocatore. E verrà aggiornato anche uno
     * stato apposito dal quale il controller potrà ottenere le informazioni per vedere se il player ha scelto giusto
     * o no.
     */
    @Override
    public void activate() {
        List<ResourceList> tempResourceChoices = new ArrayList<>(choices);
        ResourceList choice;

        for (int i = 0; i < numberOfDifferentPrivileges; i++) {
            do {
                List<String> resourceStringChoices = new ArrayList<>();
                for (ResourceList resourcelist : tempResourceChoices
                        ) {
                    resourceStringChoices.add(resourcelist.toString());
                }

                //fare richiesta alla view per la scelta del privilegio
                String string = player.getPlayerId() + ":Quale risorsa del privilegio vuoi ottenere?";

                //setto la lista di risorse rimaste al player tra cui scegliere( cosi la view non può mentirmi visto che poi posso usare questa variabile per controllare la risposta ceh ricevo dalla view
                player.getModel().getModelChoices().getLastModelStateForControl().setResourceListToControl(tempResourceChoices);


                player.getModel().notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoicePrivilegeResource(resourceStringChoices)));
                choice = player.getModel().getModelChoices().waitResourceChosenFromPrivilege();

                if (choice == null) {  //TIMER SCADUTO
                    actionTimerEnded = true;
                    player.getModel().notifyViews(new MVStringToPrint(null, true, "Timer vecchio giocatore scaduto"));
                    break;
                }

            } while (!tempResourceChoices.contains(choice));

            if (!actionTimerEnded) {   //TIMER NON SCADUTO

                if (areDifferent) {
                    super.player.getPlayerActionSet().getTempResources(choice);
                    tempResourceChoices.remove(choice);
                } else {
                    super.player.getPlayerActionSet().getTempResources(choice);
                }
                player.getModel().notifyViews(new MVUpdateState("Aggiornato stato player resources", player.getStatePlayerResources()));
            }
        }

        actionTimerEnded=false;
        resetAreDifferent();
        resetNumberOfDifferentPrivileges();

    }
}
