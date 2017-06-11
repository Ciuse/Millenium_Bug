package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.StateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.constants.CardColor;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getTower_Identical_Box_Max;

/**
 * Created by giulia on 09/06/2017.
 */
public class StateViewTower {
    private final CardColor cardColor;
    private final List<StateViewTowerCardBox> stateViewTowerCardBox = new ArrayList<>();

    public StateViewTower(CardColor cardColor) {
        this.cardColor = cardColor;
        for (int i = 0; i < getTower_Identical_Box_Max(); i++) {
            stateViewTowerCardBox.add(new StateViewTowerCardBox(cardColor, i));
        }
    }
    public void updateState(StateCardBox stateCardBox) {
        if (stateCardBox.getTowerFloor() != -1) {
            if (stateCardBox.getCardColor().equals(this.cardColor)) {
                for (StateViewTowerCardBox stateViewTowerCardBox : stateViewTowerCardBox
                        ) {
                    if (stateCardBox.getTowerFloor() == stateViewTowerCardBox.getTowerFloor())
                        stateViewTowerCardBox.updateState(stateCardBox);
                }
            }

        }
    }
}
