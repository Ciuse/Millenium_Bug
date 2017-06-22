package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.messages.messageMV.MVAskChoice;
import it.polimi.ingsw.ps31.messages.messageMV.MVUpdateState;
import it.polimi.ingsw.ps31.model.bonus.Bonus;
import it.polimi.ingsw.ps31.model.choiceType.ChoicePrivilegeResource;
import it.polimi.ingsw.ps31.model.gameResource.*;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionChooseDifferentPrivilege extends Action {
    private Integer numberOfDifferentPrivileges = null;
    private Boolean areDifferent=null;
    private final List<ResourceList> choices;

    /* Constructor */
    public ActionChooseDifferentPrivilege(Player player, ActionControlSet actionControlSet) {
        super(player, actionControlSet);

        //Creo la lista di possibili scelte per i privilegi
        this.choices = new ArrayList<>();

        List<Resource> resources = new ArrayList<>();

        resources.add(new Wood(1));
        resources.add(new Stone(1));
        this.choices.add(new ResourceList(resources));

        resources.clear();
        resources.add(new Servant(2));
        this.choices.add(new ResourceList(resources));

        resources.clear();
        resources.add(new Coin(2));
        this.choices.add(new ResourceList(resources));

        resources.clear();
        resources.add(new MilitaryStrength(2));
        this.choices.add(new ResourceList(resources));

        resources.clear();
        resources.add(new FaithPoint(1));
        this.choices.add(new ResourceList(resources));
    }

    /* Setters & Getters */
    public Integer getNumberOfDifferentPrivileges()
    {
        return numberOfDifferentPrivileges;
    }

    public void setAreDifferent(boolean areDifferent) {
        this.areDifferent = areDifferent;
    }

    public void setNumberOfDifferentPrivileges(Integer numberOfDifferentPrivileges)
    {
        this.numberOfDifferentPrivileges = numberOfDifferentPrivileges;
    }
    public void resetAreDifferent(){
        this.areDifferent=null;
    }
    public void resetNumberOfDifferentPrivileges(){
        this.numberOfDifferentPrivileges=null;
    }

    /* Class Methods */
    @Override
    public void activate()
    {
        List<ResourceList> tempResourceChoices= new ArrayList<>(choices);
        //Controllo che i parametri siano settati
        if ( this.numberOfDifferentPrivileges == null || this.numberOfDifferentPrivileges < 0)
        {
            //TODO:gestire
        }else
        {
            ResourceList choice;
            for (Integer i = 0; i < this.numberOfDifferentPrivileges; i++) {
                do {
                    List<String> resourceStringChoices= new ArrayList<>();
                    for (ResourceList resourcelist:tempResourceChoices
                         ) {
                        resourceStringChoices.add(resourcelist.toString());
                    }
                    //fare richiesta alla view per la scelta del privilegio
                    String string = player.getPlayerId() + ":Quale risorsa del privilegio vuoi ottenere?";
                    notifyViews(new MVAskChoice(player.getPlayerId(), string, new ChoicePrivilegeResource(resourceStringChoices)));
                    choice = super.waitResourceChosen();

                } while (!tempResourceChoices.contains(choice));

                if (areDifferent) {
                    super.player.getPlayerActionSet().getResources(choice);
                    tempResourceChoices.remove(choice);
                } else {
                    super.player.getPlayerActionSet().getResources(choice);
                }
                resetAreDifferent();
                resetNumberOfDifferentPrivileges();
                super.notifyViews(new MVUpdateState("Aggiornato stato player resources",player.getStatePlayerResources()));
            }
        }
    }
}
