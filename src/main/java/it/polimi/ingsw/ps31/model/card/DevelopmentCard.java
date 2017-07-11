package it.polimi.ingsw.ps31.model.card;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.model.effect.ActiveEffect;
import it.polimi.ingsw.ps31.model.effect.Effect;
import it.polimi.ingsw.ps31.model.effect.EffectList;
import it.polimi.ingsw.ps31.model.gameResource.ResourceList;
import it.polimi.ingsw.ps31.model.player.Player;
import it.polimi.ingsw.ps31.model.stateModel.StateDevelopmentCard;
import it.polimi.ingsw.ps31.model.stateModel.StateEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 *
 * Classe astratta che rappresenta una carta sviluppo.
 * Le carte sono dotate oltre di un colore e un id, di una lista contentente più risorse di costi e
 * di due liste per gli effetti.
 *
 * @see ResourceList
 * @see EffectList
 * @see ActiveEffect
 */
public abstract class DevelopmentCard extends Card implements ActiveEffect {
    private final int cardId;
    private final CardColor cardColor;
    private final int period;
    private final List<ResourceList> costList;  // il costo viene visto come una lista di risorse
    private  EffectList immediateEffectList;
    private  EffectList permanentEffectList;

    public DevelopmentCard(int cardId, String name, CardColor cardColor, int period, List<ResourceList> costList, EffectList immediateEffectList, EffectList permanentEffectList) {
        super(name);
        this.cardId = cardId;
        this.cardColor = cardColor;
        this.period = period;
        this.costList = costList;
        this.immediateEffectList = immediateEffectList;
        this.permanentEffectList = permanentEffectList;
    }

    /*Getters*/
    public int getCardId() {
        return this.cardId;
    }

    public CardColor getCardColor() {
        return this.cardColor;
    }

    public int getPeriod() {
        return this.period;
    }

    public List<ResourceList> getCostList() {   //ritorno una copia al riferimento alla lista per non farla modificare
        return new ArrayList<>(this.costList);
    }

    public EffectList getImmediateEffectList() {
        return this.immediateEffectList;
    }

    public EffectList getPermanentEffectList() {
        return this.permanentEffectList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        DevelopmentCard that = (DevelopmentCard) o;

        if (cardId != that.cardId) return false;
        if (period != that.period) return false;
        if (cardColor != that.cardColor) return false;
        if (costList != null ? !costList.equals(that.costList) : that.costList != null) return false;
        if (immediateEffectList != null ? !immediateEffectList.equals(that.immediateEffectList) : that.immediateEffectList != null)
            return false;
        return permanentEffectList != null ? permanentEffectList.equals(that.permanentEffectList) : that.permanentEffectList == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + cardId;
        result = 31 * result + cardColor.hashCode();
        result = 31 * result + period;
        result = 31 * result + (costList != null ? costList.hashCode() : 0);
        result = 31 * result + (immediateEffectList != null ? immediateEffectList.hashCode() : 0);
        result = 31 * result + (permanentEffectList != null ? permanentEffectList.hashCode() : 0);
        return result;
    }

    /**
     * Alle carte quando viene richiesto attivano i propri effetti sul player
     * @param player
     */
    @Override
    public void activeEffectList(Player player) {
        if (this.immediateEffectList != null) {
            if (immediateEffectList.isNotNull()) {
                for (int i = 0; i < this.immediateEffectList.size(); i++) {
                    this.immediateEffectList.get(i).activate(player);
                }
                player.addTempResoucesToPlayerResources();
            }
        }

        if (this.permanentEffectList != null) {
            if (permanentEffectList.isNotNull()) {
                for (int i = 0; i < this.permanentEffectList.size(); i++) {
                    this.permanentEffectList.get(i).activate(player);
                }
            }
        }
    }

    public StateDevelopmentCard getStateDevelopmentCard() {
        List<String> stringCosts = new ArrayList<>();
        for (ResourceList resourceList : costList
                ) {
            stringCosts.add(resourceList.toString());
        }

        List<StateEffect> stateImmediateEffects = new ArrayList<>();
        for (Effect effect : immediateEffectList.getEffectList()
                ) {
            stateImmediateEffects.add(new StateEffect(effect));
        }

        List<StateEffect> statePermanentEffects = new ArrayList<>();
        for (Effect effect : permanentEffectList.getEffectList()
                ) {

            statePermanentEffects.add(new StateEffect(effect));
        }
        return new StateDevelopmentCard(super.getName(), cardId, cardColor, stateImmediateEffects, statePermanentEffects, stringCosts);
    }

}
