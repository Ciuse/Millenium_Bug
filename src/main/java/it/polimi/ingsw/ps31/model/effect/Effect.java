package it.polimi.ingsw.ps31.model.effect;

import it.polimi.ingsw.ps31.model.Model;
import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.gameResource.Resource;
import it.polimi.ingsw.ps31.model.player.Player;

import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class Effect extends Model implements EffectActivation{
    private Player player;

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Effect effect = (Effect) o;

        return player != null ? player.equals(effect.player) : effect.player == null;
    }

    @Override
    public int hashCode() {
        return player.hashCode();
    }

    public abstract String nameString();

    public abstract List<String> resourcesToPayString();

    public abstract List<String> resourcesToGainString();

    public abstract String resourceToGainString();

    public abstract String requiredResourceString();

    public abstract int getBasicValue();

    public abstract int getDiceValue();

    public abstract CardColor getCardColor();

    public abstract Effect getGetResource();

    public abstract GetResourceEffect getGetResourceEffect();

    public abstract String resourceDiscountString();

}
