package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.constants.CardColor;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getPersonal_Board_Identical_Box_Max;

/**
 * Created by giulia on 08/06/2017.
 */
public class StateViewPersonalCardBox {
    private String name;
    private int cardId;
    private final CardColor cardColor;
    private final int value;

    public StateViewPersonalCardBox(CardColor cardColor, int value) {
        this.cardColor = cardColor;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void updateState(StateCardBox stateCardBox){
            this.name = stateCardBox.getName();
            this.cardId = stateCardBox.getCardId();
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
