package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.constants.CardColor;
import it.polimi.ingsw.ps31.server.message.StateVisitor;

/**
 * Created by giulia on 06/06/2017.
 */
public class StateCardBox extends StateInfo {
    private final String name;
    private final int cardId;
    private final CardColor cardColor;

    public StateCardBox(String name, int cardId, CardColor cardColor) {
        this.name = name;
        this.cardId = cardId;
        this.cardColor = cardColor;
    }

    public String getName() {
        return name;
    }

    public int getCardId() {
        return cardId;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
