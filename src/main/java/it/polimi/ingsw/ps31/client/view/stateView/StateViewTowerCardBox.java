package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.stateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.constants.CardColor;

/**
 * Created by giulia on 11/06/2017.
 */
public class StateViewTowerCardBox {
    private String name;
    private int cardId;
    private final CardColor cardColor;
    private final int towerFloor;

    public StateViewTowerCardBox(CardColor cardColor, int towerFloor) {
        this.cardColor = cardColor;
        this.towerFloor = towerFloor;
    }

    public int getTowerFloor() {
        return towerFloor;
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

    public void updateState(StateCardBox stateCardBox){
        this.name = stateCardBox.getName();
        this.cardId = stateCardBox.getCardId();
    }
}

