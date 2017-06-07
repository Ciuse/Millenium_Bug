package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.gameResource.*;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 18/05/2017.
 */
public class ActionChooseDifferentPrivilege extends Action {
    private Integer numberOfDifferentPrivileges = null;
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

    public void setNumberOfDifferentPrivileges(Integer numberOfDifferentPrivileges)
    {
        this.numberOfDifferentPrivileges = numberOfDifferentPrivileges;
    }

    /* Class Methods */
    @Override
    public void activate()
    {
        //Controllo che i parametri siano settati
        if ( this.numberOfDifferentPrivileges == null || this.numberOfDifferentPrivileges < 0)
        {
            //TODO:gestire
        }else
        {
            List<ResourceList> choiced = new ArrayList<>();

            ResourceList choice;
            for (Integer i = 0; i < this.numberOfDifferentPrivileges; i++)
            {
                do
                {
                    //TODO: fare richiesta alla view per la scelta del privilegio
                    choice = new ResourceList(new Wood(1)); //Non così ma dalla view

                }while (choiced.contains(choice));

                super.player.getPlayerActionSet().getResources(choice);
                choiced.add(choice);
            }
        }
    }
}
