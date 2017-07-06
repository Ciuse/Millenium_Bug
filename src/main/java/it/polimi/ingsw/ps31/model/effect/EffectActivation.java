package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.player.Player;

/**
 * Created by Giuseppe on 09/05/2017.
 *
 * Interfaccia implementata da ogni effetto, per indicare il fatto che ogni effetto
 * deve specificare il modo in cui si attiva su un player * @see Effect
 *
 * @see Effect
 */
public interface EffectActivation {
    void activate(Player player);

}
