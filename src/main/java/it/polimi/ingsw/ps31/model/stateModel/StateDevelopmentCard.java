package it.polimi.ingsw.ps31.model.stateModel;

import it.polimi.ingsw.ps31.model.constants.CardColor;

import java.util.List;

/**
 * Created by giulia on 13/06/2017.
 */
public class StateDevelopmentCard extends StateType {
    private String cardName = null;
    private int cardId = 0;
    private final CardColor cardColor;
    private List<StateEffect> immediateEffectList = null;
    private List<StateEffect> permanentEffectList = null;
    private List<String> stringCosts = null;

    public StateDevelopmentCard(String cardName, int cardId, CardColor cardColor, List<StateEffect> immediateEffectList, List<StateEffect> permanentEffectList, List<String> stringCosts) {
        this.cardName = cardName;
        this.cardId = cardId;
        this.cardColor = cardColor;
        this.immediateEffectList = immediateEffectList;
        this.permanentEffectList = permanentEffectList;
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

    public List<StateEffect> getImmediateEffectList() {
        return immediateEffectList;
    }

    public List<StateEffect> getPermanentEffectList() {
        return permanentEffectList;
    }

    public List<String> getStringCosts() {
        return stringCosts;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}

