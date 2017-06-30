package it.polimi.ingsw.ps31.model.stateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 30/06/2017.
 */
public class StateAllDevelopmentCard extends StateType {
    List<StateDevelopmentCard> stateDevelopmentCardList=new ArrayList<>();

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
