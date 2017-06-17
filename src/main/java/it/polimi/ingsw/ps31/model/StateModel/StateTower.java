package it.polimi.ingsw.ps31.model.StateModel;

import it.polimi.ingsw.ps31.model.constants.CardColor;

import java.util.List;

/**
 * Created by giulia on 09/06/2017.
 */
public class StateTower extends StateType {
    private final CardColor color;
    private final List<StateCardBox> towerCardSpaceList;



    public StateTower(CardColor color, List<StateCardBox> towerCardSpaceList) {
        this.color = color;
        this.towerCardSpaceList = towerCardSpaceList;
    }

    public CardColor getColor() {
        return color;
    }

    public List<StateCardBox> getTowerCardSpaceList() {
        return towerCardSpaceList;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
