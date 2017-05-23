package it.polimi.ingsw.ps31.Effect;

import it.polimi.ingsw.ps31.Effect.GenericEffect;
import it.polimi.ingsw.ps31.Player.Player;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class Effect implements GenericEffect {
    private Player player;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Effect effect = (Effect) o;

        return player != null ? player.equals(effect.player) : effect.player == null;
    }


}
