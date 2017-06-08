package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.constants.CardColor;

/**
 * Created by giulia on 08/06/2017.
 */
public class StateViewCardBox {
    private String name;
    private int cardId;
    private final CardColor cardColor;

    public StateViewCardBox(CardColor cardColor) {
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
}
