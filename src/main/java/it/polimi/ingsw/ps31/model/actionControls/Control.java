package it.polimi.ingsw.ps31.model.actionControls;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Francesco on 24/05/2017.
 *
 * Classe padre di tutti i controlli presenti nel gioco e associati a un player, poichè ogni
 * controllo lungo il corso del gioco potrà subire delle modifiche per mezzo di Bonus, Malus,
 * Carte Leader, o Effetti Permanenti di Carte Sviuppo.
 *
 *
 * @see it.polimi.ingsw.ps31.model.player.PlayerActionSet
 * @see Player
 */
public abstract class Control{
    protected final Player player;

    public abstract boolean execute();

    public Control(Player player)
    {
        this.player = player;
    }

    public abstract String getControlStringError();
}
