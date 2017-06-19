package it.polimi.ingsw.ps31.client.view.stateView;

import it.polimi.ingsw.ps31.model.stateModel.StateCardBox;
import it.polimi.ingsw.ps31.model.constants.CardColor;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.ingsw.ps31.client.view.stateView.ViewStaticInformation.getTower_Identical_Box_Max;

/**
 * Created by giulia on 09/06/2017.
 */
public class StateViewTower {
    private final CardColor towerColor;
    private final List<StateViewTowerCardBox> stateViewTowerCardBox = new ArrayList<>();

    public StateViewTower(CardColor towerColor) {
        this.towerColor = towerColor;
        for (int i = 0; i < getTower_Identical_Box_Max(); i++) {
            stateViewTowerCardBox.add(new StateViewTowerCardBox(towerColor, i));
        }
    }

    public CardColor getTowerColor() {
        return towerColor;
    }

    public List<StateViewTowerCardBox> getStateViewTowerCardBox() {
        return stateViewTowerCardBox;
    }

    public void updateState(StateCardBox stateCardBox) {
        if (stateCardBox.getTowerFloor() != -1) {
            if (stateCardBox.getCardColor().equals(this.towerColor)) {
                for (StateViewTowerCardBox stateViewTowerCardBox : stateViewTowerCardBox
                        ) {
                    if (stateCardBox.getTowerFloor() == stateViewTowerCardBox.getTowerFloor())
                        stateViewTowerCardBox.updateState(stateCardBox);
                }
            }

        }
    }
}
