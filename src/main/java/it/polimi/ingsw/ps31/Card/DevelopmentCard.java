package it.polimi.ingsw.ps31.Card;

import it.polimi.ingsw.ps31.Constants.CardColor;
import it.polimi.ingsw.ps31.Effect.Effect;
import it.polimi.ingsw.ps31.GameThings.ResourceList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 09/05/2017.
 */
public abstract class DevelopmentCard extends Card {
    private final int cardId;
    private final CardColor cardColor;
    private final int period;
    private final List<ResourceList> costList;  // il costo viene visto come una lista di risorse
    private final Effect immediateEffect;
    private final Effect permanentEffect;

    public DevelopmentCard(int cardId, String name, CardColor cardColor, int period, List<ResourceList> costList, Effect immediateEffect, Effect permanentEffect) {
        super(name);
        this.cardId = cardId;
        this.cardColor = cardColor;
        this.period = period;
        this.costList = costList;
        this.immediateEffect = immediateEffect;
        this.permanentEffect = permanentEffect;
    }

    public int getCardId() {
        return this.cardId;
    }
    public CardColor getCardColor(){
        return this.cardColor;
    }
    public int getPeriod(){
        return this.period;
    }
    public List<ResourceList> getCostList(){   //ritorno una copia al riferimento alla lista per non farla modificare
        return new ArrayList<>(this.costList);
    }
    public Effect getImmediateEffect(){
        return this.immediateEffect;
    }
    public Effect getPermanentEffect(){
        return this.permanentEffect;
    }
}
