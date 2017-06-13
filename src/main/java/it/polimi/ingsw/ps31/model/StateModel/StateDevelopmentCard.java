package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.constants.CardColor;

import it.polimi.ingsw.ps31.model.constants.CardColor;

import java.util.List;

/**
 * Created by giulia on 13/06/2017.
 */
public class StateDevelopmentCard extends StateInfo {
    private String cardName = null;
    private int cardId = -1;
    private final CardColor cardColor;
    private List<StateEffect> stringImmediateEffects = null;
    private List<StateEffect> stringPermanentEffects = null;
    private List<String> stringCosts = null;

    public StateDevelopmentCard(String cardName, int cardId, CardColor cardColor, List<StateEffect> stringImmediateEffects, List<StateEffect> stringPermanentEffects,List<String> stringCosts) {
        this.cardName = cardName;
        this.cardId = cardId;
        this.cardColor = cardColor;
        this.stringImmediateEffects = stringImmediateEffects;
        this.stringPermanentEffects = stringPermanentEffects;
        this.stringCosts = stringCosts;
    }

    public String getCardName() {
        return cardName;
    }

    public int getCardId() {
        return cardId;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public List<StateEffect> getStringImmediateEffects() {
        return stringImmediateEffects;
    }

    public List<StateEffect> getStringPermanentEffects() {
        return stringPermanentEffects;
    }

    public List<String> getStringCosts() {
        return stringCosts;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}

