package it.polimi.ingsw.ps31.model.stateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giuseppe on 30/06/2017.
 */
public class StateAllDevelopmentCard extends StateType {
    private List<StateDevelopmentCard> stateDevelopmentCardList=new ArrayList<>();

    public List<StateDevelopmentCard> getStateDevelopmentCardList() {
        return stateDevelopmentCardList;
    }

    public StateAllDevelopmentCard(List<StateDevelopmentCard> stateDevelopmentCardList) {
        this.stateDevelopmentCardList = stateDevelopmentCardList;
    }

    @Override
    public void acceptState(StateVisitor stateVisitor) {
        stateVisitor.visit(this);
    }
}
