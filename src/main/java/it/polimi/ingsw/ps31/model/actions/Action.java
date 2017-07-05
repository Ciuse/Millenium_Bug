package it.polimi.ingsw.ps31.model.actions;

import it.polimi.ingsw.ps31.model.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 18/05/2017.
 *
 * Classe padre di tutte le azioni che un player può eseguire, o direttamente o indirettamente tramite
 * il comportamento di un'altra azione. Ogni player ha il suo set di azioni unico, in modo da poterle
 * modificare a seconda degli effetti permanenti e delle scomuniche che ha senza modificare il comportamento
 * delle azioni degli altri player.
 * Poichè tutte le azioni deovno condividere lo stesso costruttore, i vari parametri di ogni azione devono
 * essere impostati tramite appositi metodi setter prima di invocarne l'attivazione
 *
 * @see ActionControlSet
 * @see Player
 */
public abstract class Action implements PlayerActions{
    /** Il player a cui l'azione appartiene */
    protected final Player player;

    /** Il control set da cui attivare i controlli relativi all'azione */
    protected final  ActionControlSet actionControlSet;

    //private List<Action> actionList= new ArrayList<>();

    /* Constructor */
    public Action(Player player, ActionControlSet actionControlSet)
    {
        this.player = player;
        this.actionControlSet = actionControlSet;
        //actionList.add(this);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
